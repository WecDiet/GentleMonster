package com.gentlemonster.DTO.Response.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {
    private String name;
    private String description;
}

