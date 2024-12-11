package com.gentlemonster.DTO.Request.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditRoleRequest {
    private String name;
    private String description;
}
