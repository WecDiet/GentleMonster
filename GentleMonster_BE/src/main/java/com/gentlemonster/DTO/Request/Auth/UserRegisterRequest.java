package com.gentlemonster.DTO.Request.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data //toString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @JsonProperty("email")
    @NotBlank(message = "Mail is required")
    private String email;
    @NotNull(message = "Confirm email is required")
    private String confirmEmail;
    @NotBlank(message = "Password can not be blank")
    private String password;
    @NotNull(message = "Confirm password is required")
    private String confirmPassword;
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Phone number is required")
    private String phone;
    @NotNull(message = "day is required not empty !")
    int day;
    @NotNull(message = "month is required not empty !")
    int month;
    @NotNull(message = "year is required not empty !")
    int year;
    @NotNull(message = "Country is required")
    private String country;
}
