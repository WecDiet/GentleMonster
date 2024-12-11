package com.gentlemonster.Browser.Admin.Controller;

import com.gentlemonster.Constant.Endpoint;
import com.gentlemonster.DTO.Request.Role.AddRoleRequest;
import com.gentlemonster.DTO.Request.Role.EditRoleRequest;
import com.gentlemonster.DTO.Request.Role.RoleRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.Role.RoleResponse;
import com.gentlemonster.Entity.Role;
import com.gentlemonster.Service.Role.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.Role.BASE)
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<PagingResponse<?>> getAllRole(@ModelAttribute RoleRequest roleRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(new PagingResponse<>(null, errorMessages, 0, (long) 0));
        }
        return ResponseEntity.ok(roleService.getRole(roleRequest));
    }

    @PostMapping(Endpoint.Role.NEW)
    public ResponseEntity<APIResponse<Role>> addRole(@Valid @RequestBody AddRoleRequest addRoleRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            APIResponse<Role> errorResponse = new APIResponse<>(null, errorMessages);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok(roleService.createRole(addRoleRequest));

    }

    @PutMapping(Endpoint.Role.ID)
    public ResponseEntity<APIResponse<RoleResponse>> updateRole(@PathVariable String roleID, @RequestBody EditRoleRequest editRoleRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            APIResponse<RoleResponse> errorResponse = new APIResponse<>(null, errorMessages);
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return ResponseEntity.ok(roleService.editRole(roleID,editRoleRequest));
    }

    @DeleteMapping(Endpoint.Role.ID)
    public ResponseEntity<APIResponse<Boolean>> deleteRole(@PathVariable String roleID) {
        return ResponseEntity.ok(roleService.deleteRole(roleID));
    }
}
