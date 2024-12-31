package com.gentlemonster.DTO.Request.Category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditCategoryRequest {
    private String name;
    private String image;
    private String category;
    private String slug;
    private boolean active;
}
