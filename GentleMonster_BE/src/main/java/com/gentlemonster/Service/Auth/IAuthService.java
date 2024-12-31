package com.gentlemonster.Service.Auth;

import com.gentlemonster.DTO.Request.Auth.UserRegisterRequest;
import com.gentlemonster.Entity.User;

public interface IAuthService {
    User registerUser(UserRegisterRequest userRegisterRequest) throws Exception;
    String login(String email, String password) throws Exception;
}
