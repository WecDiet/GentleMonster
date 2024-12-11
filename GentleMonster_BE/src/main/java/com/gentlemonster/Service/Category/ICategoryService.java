package com.gentlemonster.Service.Category;

import com.gentlemonster.DTO.Request.Category.AddCategoryRequest;
import com.gentlemonster.DTO.Request.Category.CategoryRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.Category.CategoryResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.Entity.Category;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface ICategoryService {
    PagingResponse<List<CategoryResponse>> getCategory(@ModelAttribute CategoryRequest categoryRequest);
    APIResponse<Category> addCategory(AddCategoryRequest addCategoryRequest);
}
