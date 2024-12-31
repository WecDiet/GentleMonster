package com.gentlemonster.DTO.Request.User;

import com.gentlemonster.Entity.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddUserRequest {
    @NotEmpty(message = "firstName is required not empty !")
    private String firstName;
    @NotEmpty(message = "title is required not empty !")
    private String title;
    @NotEmpty(message = "lastName is required not empty !")
    private String lastName;
    @NotEmpty(message = "email is required not empty !")
    private String email;
    @NotEmpty(message = "employeeCode is required not empty !")
    private String employeeCode;
    @NotEmpty(message = "phoneNumber is required not empty !")
    private String phoneNumber;
    private int day;
    private int month;
    private int year;
    @NotEmpty(message = "street is required not empty !")
    private String street;
    @NotEmpty(message = "ward is required not empty !")
    private String ward;
    @NotEmpty(message = "district is required not empty !")
    private String district;
    @NotEmpty(message = "city is required not empty !")
    private String city;
    @NotEmpty(message = "country is required not empty !")
    private String country;
    @NotEmpty(message = "password is required not empty !")
    private String password;
    private String photos;
    private boolean status;
    @NotEmpty(message = "roles is required not empty !")
    private Set<Role> roles;
}

