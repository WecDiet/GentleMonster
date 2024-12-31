package com.gentlemonster.DTO.Response.Product;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private String name;
    private String image;
    private String description;
    private double price;
    private int quantity;
    private Set<String> images;
    private boolean active;
    private String slug;
    private String category;
}
