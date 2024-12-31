package com.gentlemonster.Service.ProductType;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.ProductType.AddProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.EditProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.ProductTypeRequest;
import com.gentlemonster.DTO.Request.User.EditUserRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.ProductType.ProductTypeResponse;
import com.gentlemonster.Entity.Category;
import com.gentlemonster.Entity.ProductType;
import com.gentlemonster.Exception.DataExistedException;
import com.gentlemonster.Repository.ICategoryRepository;
import com.gentlemonster.Repository.IProductTypeRepository;
import com.gentlemonster.Repository.Specification.ProductTypeSpecification;
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


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductTypeService implements IProductTypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductTypeRepository iProductTypeRepository;
    @Autowired
    private LocalizationUtil localizationUtil;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Override
    public PagingResponse<List<ProductTypeResponse>> getProductType(@ModelAttribute ProductTypeRequest productTypeRequest) {
        ArrayList<ProductTypeResponse> productTypeResponseList;
        List<ProductType> productTypeList;
        Pageable pageable;
        if(productTypeRequest.getPage() == 0 && productTypeRequest.getLimit() == 0){
            Specification<ProductType> productTypeSpecification = ProductTypeSpecification.fillterProductType(productTypeRequest.getName());
            productTypeList = iProductTypeRepository.findAll(productTypeSpecification);
            productTypeResponseList = new ArrayList<>(productTypeList.stream()
                    .map(productType -> modelMapper.map(productType, ProductTypeResponse.class))
                    .toList());
            List<String> messages = new ArrayList<>();
            messages.add(localizationUtil.getLocalizedMessage(MessageKey.PRODUCT_TYPE_GET_SUCCESS));
            return new PagingResponse<>(productTypeResponseList, messages, 1, (long) productTypeResponseList.size());
        }else{
            productTypeRequest.setPage(Math.max(productTypeRequest.getPage(),1));
            pageable = PageRequest.of(productTypeRequest.getPage() - 1, productTypeRequest.getLimit());
        }
        Specification<ProductType> productTypeSpecification = ProductTypeSpecification.fillterProductType(productTypeRequest.getName());
        Page<ProductType> productTypePage = iProductTypeRepository.findAll(productTypeSpecification, pageable);
        productTypeList = productTypePage.getContent();
        productTypeResponseList = new ArrayList<>(productTypeList.stream()
                .map(productType -> modelMapper.map(productType, ProductTypeResponse.class))
                .toList());
        return new PagingResponse<>(productTypeResponseList, null, productTypePage.getTotalPages(), productTypePage.getTotalElements());
    }

    @Override
    public APIResponse<ProductType> addType(AddProductTypeRequest addProductTypeRequest) {
        ProductType productType = modelMapper.map(addProductTypeRequest, ProductType.class);
        Category category = iCategoryRepository.findById(UUID.fromString(addProductTypeRequest.getCategory())).orElseThrow(() -> new DataExistedException("Category not found with ID" + addProductTypeRequest.getCategory()));
        productType.setCategory(category);
        iProductTypeRepository.save(productType);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.PRODUCT_TYPE_CREATE_SUCCESS));
        return new APIResponse<>(productType, messages);
    }

    @Override
    public APIResponse<ProductType> updateType(String productTypeID,EditProductTypeRequest editProductTypeRequest) {
        Date now = new Date(System.currentTimeMillis());
        ProductType productType = iProductTypeRepository.findById(UUID.fromString(productTypeID)).orElseThrow(() -> new DataExistedException("Product Type not found with ID" + productTypeID));
        modelMapper.map(editProductTypeRequest, productType);
        productType.setModifiedDate(now);
        iProductTypeRepository.save(productType);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.PRODUCT_TYPE_UPDATE_SUCCESS));
        return new APIResponse<>(productType, messages);
    }

    @Override
    public APIResponse<Boolean> deleteType(String productTypeID) {
        ProductType productType = iProductTypeRepository.findById(UUID.fromString(productTypeID)).orElseThrow(() -> new DataExistedException("Product Type not found with ID" + productTypeID));
        iProductTypeRepository.delete(productType);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtil.getLocalizedMessage(MessageKey.PRODUCT_TYPE_DELETE_SUCCESS));
        return new APIResponse<>(true, messages);
    }
}
