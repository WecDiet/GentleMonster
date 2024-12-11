package com.gentlemonster.DTO.Request.Category;

import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private int limit= -1;
    private int page = -1;
    private String name;
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Category chỉ được chứa chữ thường, số, và dấu '-'")
    private String category;
    @Pattern(regexp = "^[a-z0-9-]+$", message = "Slug chỉ được chứa chữ thường, số, và dấu '-'")
    private String slug;
}
