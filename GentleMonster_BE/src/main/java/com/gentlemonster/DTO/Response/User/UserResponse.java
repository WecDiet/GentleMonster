package com.gentlemonster.DTO.Response.User;

import com.gentlemonster.Entity.Role;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String title;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String employeeCode;
    private String phoneNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private String birthDay;
    private String password;
    private String photos;
    private boolean status;
    private List<Role> roles;
}
