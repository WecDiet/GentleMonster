package com.gentlemonster.Service.Role;

import com.gentlemonster.DTO.Request.Role.AddRoleRequest;
import com.gentlemonster.DTO.Request.Role.EditRoleRequest;
import com.gentlemonster.DTO.Request.Role.RoleRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.Role.RoleResponse;
import com.gentlemonster.Entity.Role;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface IRoleService {
    PagingResponse<List<RoleResponse>> getRole(@ModelAttribute RoleRequest roleRequest);
    APIResponse<Role> createRole(AddRoleRequest addRoleRequest);
    APIResponse<RoleResponse> editRole (String roleID, EditRoleRequest editRoleRequest);
    APIResponse<Boolean> deleteRole(String roleID);
}

