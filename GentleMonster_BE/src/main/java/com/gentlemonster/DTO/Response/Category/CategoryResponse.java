package com.gentlemonster.DTO.Response.Category;

import com.gentlemonster.DTO.Response.Product.ProductResponse;
import com.gentlemonster.DTO.Response.ProductType.ProductTypeResponse;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private UUID id;
    private String name;
    private String image;
    private String category;
    private String slug;
    private boolean active;
    private Set<ProductTypeResponse> productType;
}
