package com.gentlemonster.DTO.Response.ProductType;
import com.gentlemonster.Entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeResponse {
    private String id;
    private String name;
    private String description;
    private String category;
    private boolean active;
    private List<Product> products = new ArrayList<>();
}
