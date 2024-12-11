package com.gentlemonster.Service.Product;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.Product.AddProductRequest;
import com.gentlemonster.DTO.Request.Role.AddRoleRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.Entity.Category;
import com.gentlemonster.Entity.Product;
import com.gentlemonster.Exception.DataNotFoundException;
import com.gentlemonster.Repository.ICategoryRepository;
import com.gentlemonster.Repository.IProductRepository;
import com.gentlemonster.Utils.LocalizationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private LocalizationUtil localizationUtils;

    @Override
    public APIResponse<Product> createProduct(AddProductRequest addProductRequest) {
        Product productCreate = modelMapper.map(addProductRequest, Product.class);
        Category productType = iCategoryRepository.findById(UUID.fromString(addProductRequest.getCategory()))
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.CATEGORY_NOT_FOUND)));
        productCreate.setCategory(productType);
        iProductRepository.save(productCreate);

        List<String> messages = new ArrayList<>();
        messages.add(localizationUtils.getLocalizedMessage(MessageKey.PRODUCT_CREATE_SUCCESS));
        return new APIResponse<>(productCreate, messages);
    }
}
