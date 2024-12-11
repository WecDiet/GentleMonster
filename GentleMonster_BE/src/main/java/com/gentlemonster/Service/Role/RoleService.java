package com.gentlemonster.Service.Role;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.Role.AddRoleRequest;
import com.gentlemonster.DTO.Request.Role.EditRoleRequest;
import com.gentlemonster.DTO.Request.Role.RoleRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.Role.RoleResponse;
import com.gentlemonster.Entity.Role;
import com.gentlemonster.Repository.IRoleRepository;
import com.gentlemonster.Repository.Specification.RoleSpecification;
import com.gentlemonster.Utils.LocalizationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.gentlemonster.Exception.DataExistedException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private LocalizationUtil localizationUtil;

    @Override
    public PagingResponse<List<RoleResponse>> getRole(@ModelAttribute RoleRequest roleRequest) {
        ArrayList<RoleResponse> roleResponseList;
        List<Role> roleList;
        Pageable pageable;
        if(roleRequest.getPage() == 0 && roleRequest.getLimit() == 0){
            Specification<Role> roleSpecification = RoleSpecification.fillterRole(roleRequest.getName());
            roleList = iRoleRepository.findAll(roleSpecification);
            roleResponseList = new ArrayList<>(roleList.stream()
                    .map(role -> modelMapper.map(role, RoleResponse.class))
                    .collect(Collectors.toList()));
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtil.getLocalizedMessage(MessageKey.ROLE_GET_SUCCESS));
            return new PagingResponse<>(roleResponseList, messages, 1, (long) roleResponseList.size());
        }
        else{
            roleRequest.setPage(Math.max(roleRequest.getPage(), 1));
            pageable = PageRequest.of(roleRequest.getPage() - 1, roleRequest.getLimit());
        }
        Specification<Role> roleSpecification = RoleSpecification.fillterRole(roleRequest.getName());
        Page<Role> rolePage = iRoleRepository.findAll(roleSpecification, pageable);
        roleList = rolePage.getContent();
        roleResponseList = new ArrayList<>(roleList.stream()
                .map(role -> modelMapper.map(role, RoleResponse.class))
                .collect(Collectors.toList()));
        return new PagingResponse<>(roleResponseList,List.of(localizationUtil.getLocalizedMessage(MessageKey.ROLE_GET_SUCCESS)), rolePage.getTotalPages(), rolePage.getTotalElements());
    }

    @Override
    public APIResponse<Role> createRole(AddRoleRequest addRoleRequest) {
        Role roleAdd = modelMapper.map(addRoleRequest, Role.class);
        roleAdd.setName(addRoleRequest.getName());
        roleAdd.setDescription(addRoleRequest.getDescription());
        ArrayList<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.ADD_ROLE_SUCCESS));
        iRoleRepository.save(roleAdd);
        return new APIResponse<>(roleAdd,messages);
    }

    @Override
    public APIResponse<RoleResponse> editRole(String roleID, EditRoleRequest editRoleRequest) {
        Role role = iRoleRepository.findById(UUID.fromString(roleID)).orElseThrow(() -> new DataExistedException("Role not found"));
        modelMapper.map(editRoleRequest, role);
        iRoleRepository.save(role);
        RoleResponse roleResponse = modelMapper.map(role, RoleResponse.class);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.EDIT_ROLE_SUCCESS));
        return new APIResponse<>(roleResponse, messages);
    }

    @Override
    public APIResponse<Boolean> deleteRole(String roleID) {
        Role role = iRoleRepository.findById(UUID.fromString(roleID)).orElseThrow(() -> new DataExistedException("Role not found"));
        iRoleRepository.delete(role);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.DELETE_ROLE_SUCCESS));
        return new APIResponse<>(true, messages);
    }
}