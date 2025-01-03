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
public class EditUserRequest {
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "title is required not empty !")
    private String title;
    @NotEmpty(message = "Last name is required")
    private String lastName;
    @NotEmpty(message = "Email is required")
    private String email;
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;
    @NotEmpty(message = "day is required not empty !")
    private int day;
    @NotEmpty(message = "month is required not empty !")
    private int month;
    @NotEmpty(message = "year is required not empty !")
    private int year;
    @NotEmpty(message = "Password is required")
    private String password;
    private boolean status;
    @NotEmpty(message = "Role is required")
    private Set<Role> roles;
}
