package com.gentlemonster.DTO.Response.Category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private String name;
    private String image;
    private String category;
    private String slug;
    private boolean active;
}
