package com.gentlemonster.DTO.Request.Product;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddProductRequest {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private Set<String> images = new HashSet<>();
    private boolean isActive;
    private String frame;
    private String lens;
    private String shape;
    private String material;
    private double lens_Width;
    private double lens_Height;
    private double bridge;
    private String country;
    private String manufacturer;
    private String category;
}
