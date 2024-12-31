package com.gentlemonster.Browser.Admin.Controller;

import com.gentlemonster.Constant.Endpoint;
import com.gentlemonster.DTO.Request.Category.AddCategoryRequest;
import com.gentlemonster.DTO.Request.Category.CategoryRequest;
import com.gentlemonster.DTO.Request.Category.EditCategoryRequest;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.Enum.CategoryUtils;
import com.gentlemonster.Enum.SlugUtils;
import com.gentlemonster.Repository.ICategoryRepository;
import com.gentlemonster.Service.Category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.Category.BASE)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @GetMapping
    public ResponseEntity<PagingResponse<?>> getAllCategories(@ModelAttribute CategoryRequest categoryRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(new PagingResponse<>(null,  errorMessages, 0, (long) 0));
        }
        // Chuẩn hóa slug nếu có giá trị trong request
        if (categoryRequest.getSlug() != null) {
            String normalizedSlug = SlugUtils.toSlug(categoryRequest.getSlug());
            categoryRequest.setSlug(normalizedSlug);
        }
        return ResponseEntity.ok(categoryService.getCategory(categoryRequest));
    }

    @PostMapping(Endpoint.Category.NEW)
    public ResponseEntity<?> createNewCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(errorMessages);
        }
        String normalizedSlug = SlugUtils.toSlug(addCategoryRequest.getSlug());
        addCategoryRequest.setSlug(normalizedSlug);
        String normalizedCategory = CategoryUtils.toCategory(addCategoryRequest.getCategory());
        addCategoryRequest.setCategory(normalizedCategory);
        return ResponseEntity.ok(categoryService.addCategory(addCategoryRequest));
    }

    @PutMapping(Endpoint.Category.EDIT)
    public ResponseEntity<?> updateCategory(@PathVariable String categoryID ,@Valid @RequestBody EditCategoryRequest editCategoryRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(errorMessages);
        }
        String normalizedSlug = SlugUtils.toSlug(editCategoryRequest.getSlug());
        editCategoryRequest.setSlug(normalizedSlug);
        String normalizedCategory = CategoryUtils.toCategory(editCategoryRequest.getCategory());
        editCategoryRequest.setCategory(normalizedCategory);
        return ResponseEntity.ok(categoryService.editCategory(categoryID,editCategoryRequest));
    }

    @DeleteMapping(Endpoint.Category.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryID) {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryID));
    }
}
