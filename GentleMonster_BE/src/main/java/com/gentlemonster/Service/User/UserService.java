package com.gentlemonster.Service.User;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.User.AddUserRequest;
import com.gentlemonster.DTO.Request.User.EditUserRequest;
import com.gentlemonster.DTO.Request.User.UserMutiDeleteRequest;
import com.gentlemonster.DTO.Request.User.UserRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.User.UserResponse;
import com.gentlemonster.Entity.Role;
import com.gentlemonster.Entity.User;
import com.gentlemonster.Exception.DataExistedException;
import com.gentlemonster.Repository.IRoleRepository;
import com.gentlemonster.Repository.IUserRepository;
import com.gentlemonster.Repository.Specification.UserSpecification;
import com.gentlemonster.Utils.LocalizationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private LocalizationUtil localizationUtil;
    @Autowired
    private PasswordEncoder passwordEncode;
    @Autowired
    private IRoleRepository iRoleRepository;


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public PagingResponse<List<UserResponse>> getUser(@ModelAttribute UserRequest userRequest) {
        ArrayList<UserResponse> userResponseList;
        List<User> userList;
        Pageable pageable;
        if(userRequest.getPage() == 0 && userRequest.getLimit() == 0){
            Specification<User> specification = UserSpecification.filterUsers(userRequest.getRole(),userRequest.getEmployeeCode(), userRequest.getFullName(), userRequest.getEmail());
            userList = iUserRepository.findAll(specification);
            userResponseList = new ArrayList<>(userList.stream()
                    .map(user -> modelMapper.map(user, UserResponse.class))
                    .toList());
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_GET_SUCCESS));
            return new PagingResponse<>(userResponseList, messages, 1, (long) userResponseList.size());
        }else{
            userRequest.setPage(Math.max(userRequest.getPage(), 1));
            pageable = PageRequest.of(userRequest.getPage() - 1, userRequest.getLimit());
        }
        Specification<User> specification = UserSpecification.filterUsers(userRequest.getRole(),userRequest.getEmployeeCode(), userRequest.getFullName(), userRequest.getEmail());
        Page<User> userPage = iUserRepository.findAll(specification, pageable);
        userList = userPage.getContent();
        userResponseList = new ArrayList<>(userList.stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList());
        return new PagingResponse<>(userResponseList, List.of(localizationUtil.getLocalizedMessage(MessageKey.USER_GET_SUCCESS)), userPage.getTotalPages(), userPage.getTotalElements());
    }

    @Override
    public APIResponse<User> addUser(AddUserRequest addUserRequest) {
        if(iUserRepository.existsByEmail(addUserRequest.getEmail())) {
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_ALREADY_EXIST));
            logger.info("User already existed");
            return new APIResponse<>(null,messages);
        }
        if(iUserRepository.existsByPhoneNumber(addUserRequest.getPhoneNumber())){
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_PHONE_EXISTED));
            logger.info("Phone number already existed");
            return new APIResponse<>(null,messages);
        }
        User userCreated = modelMapper.map(addUserRequest, User.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            userCreated.setBirthday(dateFormat.parse(addUserRequest.getBirthday()));
        } catch (ParseException e) {
            logger.info("Invalid date format. Expected format: yyyy-MM-dd");
            throw new RuntimeException("Invalid date format. Expected format: yyyy-MM-dd");
        }
        userCreated.setPassword(passwordEncode.encode(addUserRequest.getPassword()));
        userCreated.setFullName(addUserRequest.getFirstName() + " "+addUserRequest.getMiddleName()+ " " + addUserRequest.getLastName());
        if (addUserRequest.getRoles() != null) {
            Set<Role> managedRoles = new HashSet<>();
            for (Role role : addUserRequest.getRoles()) {
                Role existingRole = iRoleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName()));
                managedRoles.add(existingRole);
            }
            userCreated.setRoles(managedRoles);
        }
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_CREATE_SUCCESS));
        iUserRepository.save(userCreated);
        logger.info("User created successfully");
        return new APIResponse<>(userCreated, messages);
    }

    @Override
    public APIResponse<UserResponse> getOneUser(String userID) {
        User user = iUserRepository.findById(UUID.fromString(userID))
                .orElseThrow(() -> new DataExistedException("User not found with ID" + userID));

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_GET_ONE_SUCCESS));
        logger.info("Get user by ID successfully");
        return new APIResponse<>(userResponse, messages);
    }

    @Override
    public APIResponse<UserResponse> updateUser(EditUserRequest editUserRequest) {
        User user = iUserRepository.findByEmail(editUserRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + editUserRequest.getEmail()));
        modelMapper.map(editUserRequest, user);
        if (editUserRequest.getRoles() != null) {
            Set<Role> updatedRoles = new HashSet<>();
            for (Role role : editUserRequest.getRoles()) {
                Role existingRole = iRoleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role.getName()));
                updatedRoles.add(existingRole);
            }
            user.setRoles(updatedRoles);
        }
        user.setPassword(passwordEncode.encode(editUserRequest.getPassword()));
        user.setFullName(editUserRequest.getFirstName() + " "+editUserRequest.getMiddleName()+ " " + editUserRequest.getLastName());
        iUserRepository.save(user);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_UPDATE_SUCCESS));
        logger.info("Update user successfully");
        return new APIResponse<>(userResponse, messages);
    }

    @Override
    public APIResponse<Boolean> deleteUser(String userID) {
        User user = iUserRepository.findById(UUID.fromString(userID))
                .orElseThrow(() -> new DataExistedException("User not found with ID" + userID));
        user.getRoles().clear();
        iUserRepository.save(user);
        iUserRepository.delete(user);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_DELETE_SUCCESS));
        logger.info("Delete user successfully");
        return new APIResponse<>(true, messages);
    }

    @Override
    public APIResponse<Boolean> deleteMutiUser(UserMutiDeleteRequest userMutiDeleteRequest) {
        List<String> userList = userMutiDeleteRequest.getId();
        userList.forEach(userID -> {
            User userDelete = iUserRepository.findById(UUID.fromString(userID))
                    .orElseThrow(() -> new DataExistedException("User not found with ID" + userID));
            userDelete.getRoles().clear();
            iUserRepository.save(userDelete);
            iUserRepository.delete(userDelete);
        });
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.USER_DELETE_SUCCESS));
        logger.info("Delete user successfully");
        return new APIResponse<>(true, messages);
    }
}
