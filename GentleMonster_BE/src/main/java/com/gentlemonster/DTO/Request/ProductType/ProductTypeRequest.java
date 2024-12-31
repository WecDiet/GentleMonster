package com.gentlemonster.DTO.Request.ProductType;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductTypeRequest {
    private int page = -1;
    private int limit = -1;
    @NotEmpty(message = "Role name is required")
    private String name;
}
