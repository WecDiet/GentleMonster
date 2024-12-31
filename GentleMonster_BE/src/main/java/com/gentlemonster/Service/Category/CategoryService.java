package com.gentlemonster.Service.Category;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.Category.AddCategoryRequest;
import com.gentlemonster.DTO.Request.Category.CategoryRequest;
import com.gentlemonster.DTO.Request.Category.EditCategoryRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.Category.CategoryResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.Entity.Category;
import com.gentlemonster.Exception.DataExistedException;
import com.gentlemonster.Repository.ICategoryRepository;
import com.gentlemonster.Repository.Specification.CategorySpecification;
import com.gentlemonster.Utils.LocalizationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LocalizationUtil localizationUtils;

    @Override
    public PagingResponse<List<CategoryResponse>> getCategory(@ModelAttribute CategoryRequest categoryRequest) {
        ArrayList<CategoryResponse> categoryResponseList;
        List<Category> categoryList;
        Pageable pageable;
        if(categoryRequest.getPage() == 0 && categoryRequest.getLimit() == 0) {
            Specification<Category> specification = CategorySpecification.fillterCategory(categoryRequest.getCategory(), categoryRequest.getSlug());
            categoryList = iCategoryRepository.findAll(specification);
            categoryResponseList = new ArrayList<>(categoryList.stream()
                    .map(category -> modelMapper.map(category, CategoryResponse.class))
                    .toList());
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_GET_SUCCESS));
            return new PagingResponse<>(categoryResponseList, messages, 1, (long) categoryResponseList.size());
        } else {
            categoryRequest.setPage(Math.max(categoryRequest.getPage(), 1));
            pageable = PageRequest.of(categoryRequest.getPage() - 1, categoryRequest.getLimit());
        }
        Specification<Category> specification = CategorySpecification.fillterCategory(categoryRequest.getCategory(), categoryRequest.getSlug());
        Page<Category> categoryPage = iCategoryRepository.findAll(specification, pageable);
        categoryList = categoryPage.getContent();
        categoryResponseList = new ArrayList<>(categoryList.stream()
                .map(category -> modelMapper.map(category, CategoryResponse.class))
                .toList());
        return new PagingResponse<>(categoryResponseList, List.of(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_GET_SUCCESS)), categoryPage.getTotalPages(), categoryPage.getTotalElements());
    }

    @Override
    public APIResponse<Category> addCategory(AddCategoryRequest addCategoryRequest) {
        Category addCategory = modelMapper.map(addCategoryRequest, Category.class);
        iCategoryRepository.save(addCategory);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_CREATE_SUCCESS));
        return new APIResponse<>(addCategory, messages);
    }

    @Override
    public APIResponse<Category> editCategory(String categoryID,EditCategoryRequest editCategoryRequest) {
        Category editCategory = iCategoryRepository.findById(UUID.fromString(categoryID)).orElseThrow(() -> new DataExistedException("Category not found with ID" + categoryID));
        modelMapper.map(editCategoryRequest, editCategory);
        iCategoryRepository.save(editCategory);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_UPDATE_SUCCESS));
        return new APIResponse<>(editCategory, messages);
    }

    @Override
    public APIResponse<Boolean> deleteCategory(String categoryID) {
        Category category = iCategoryRepository.findById(UUID.fromString(categoryID)).orElseThrow(() -> new DataExistedException("Category not found with ID" + categoryID));
        iCategoryRepository.delete(category);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_DELETE_SUCCESS));
        return new APIResponse<>(true, messages);
    }
}
