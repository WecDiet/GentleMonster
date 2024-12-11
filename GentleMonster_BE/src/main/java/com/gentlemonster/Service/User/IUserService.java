package com.gentlemonster.Service.User;

import com.gentlemonster.DTO.Request.User.AddUserRequest;
import com.gentlemonster.DTO.Request.User.EditUserRequest;
import com.gentlemonster.DTO.Request.User.UserMutiDeleteRequest;
import com.gentlemonster.DTO.Request.User.UserRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.User.UserResponse;
import com.gentlemonster.Entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface IUserService {
    PagingResponse<List<UserResponse>> getUser(@ModelAttribute UserRequest userRequest);
    APIResponse<User> addUser(AddUserRequest addUserRequest);
    APIResponse<UserResponse> getOneUser (String userID);
    APIResponse<UserResponse> updateUser(EditUserRequest editUserRequest);
    APIResponse<Boolean> deleteUser(String userID);
    APIResponse<Boolean> deleteMutiUser(UserMutiDeleteRequest userMutiDeleteRequest);
}
