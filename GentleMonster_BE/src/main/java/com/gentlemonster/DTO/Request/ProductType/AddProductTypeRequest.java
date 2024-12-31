package com.gentlemonster.DTO.Request.ProductType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddProductTypeRequest {
    private String name;
    private String description;
    private boolean active;
    private String category;
}
