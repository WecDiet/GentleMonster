package com.gentlemonster.DTO.Response.Product;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseProduct {
    private String name;
    private Set<String> images;
    private double price;
}
