package com.gentlemonster.DTO.Request.Role;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRoleRequest {
    @NotEmpty(message = "name is required not empty !")
    private String name;
    private String description;
}
