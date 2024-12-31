package com.gentlemonster.Service.Auth;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.Auth.UserRegisterRequest;
import com.gentlemonster.Entity.Role;
import com.gentlemonster.Entity.User;
import com.gentlemonster.Exception.DataExistedException;
import com.gentlemonster.Exception.DataNotFoundException;
import com.gentlemonster.Repository.IAuthRepository;
import com.gentlemonster.Repository.IRoleRepository;
import com.gentlemonster.Service.User.UserService;
import com.gentlemonster.Utils.JwtTokenUtil;
import com.gentlemonster.Utils.LocalizationUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private IAuthRepository authRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private LocalizationUtil localizationUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Override
    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) throws Exception {
        String email = userRegisterRequest.getEmail();
        String password = userRegisterRequest.getPassword();
        String confirmPassword = userRegisterRequest.getConfirmPassword();
        String confirmEmail = userRegisterRequest.getConfirmEmail();
        Optional<User> optionalUser = authRepository.findByEmail(email);
        if (password == null || confirmPassword == null) {
            throw new DataNotFoundException("password, and confirm password are required");
        }
        if(email == null || confirmEmail == null){
            throw new DataNotFoundException("email, and confirm email are required");
        }
        if (!password.equals(confirmPassword)) {
            throw new DataExistedException("Password and confirm password do not match");
        }
        if (optionalUser.isPresent()) {
            if(!optionalUser.get().getGoogleAccountId().isEmpty() || !optionalUser.get().getFacebookAccountId().isEmpty()){
                throw new Exception("This email is already registered with a social account");
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(userRegisterRequest.getYear(), userRegisterRequest.getMonth() - 1, userRegisterRequest.getDay());
        // Kiểm tra ngày hợp lệ
        Date birthday = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = dateFormat.format(birthday);

        Role userRole = roleRepository.findByName("User").orElseThrow(() -> new DataNotFoundException("User Role not set"));
        User newUser = User.builder()
                .email(email)
                .password(password)
                .title(userRegisterRequest.getTitle())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .phoneNumber(userRegisterRequest.getPhone())
                .country(userRegisterRequest.getCountry())
                .birthDay(dateFormat.parse(formattedDate))
                .status(true)
                .employeeCode(null)
                .userType(1) // 1: Google, 2: Facebook, 3: email
                .roles(Set.of(userRole))
                .build();
        authRepository.save(newUser);
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encodedPassword);
        return authRepository.save(newUser);
    }

    @Override
    @Transactional
    public String login(String email, String password) throws Exception {
        Optional<User> optionalUser = authRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException(localizationUtil.getLocalizedMessage(MessageKey.USER_NOT_EXIST));
        }
        User existingUser = optionalUser.get();
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException(localizationUtil.getLocalizedMessage(MessageKey.WRONG_INPUT));
        }
        if (!existingUser.isEnabled()) {
            throw new DataNotFoundException(localizationUtil.getLocalizedMessage(MessageKey.ACCOUNT_LOCKED));
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email,
                password,
                existingUser.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        logger.info("token generated" + jwtTokenUtil.generateToken(existingUser));
        return jwtTokenUtil.generateToken(existingUser);
    }
}
