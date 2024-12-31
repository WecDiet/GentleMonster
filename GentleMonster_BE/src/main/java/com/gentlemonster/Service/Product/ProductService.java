package com.gentlemonster.Service.Product;

import com.gentlemonster.Constant.MessageKey;
import com.gentlemonster.DTO.Request.Product.AddProductRequest;
import com.gentlemonster.DTO.Request.Role.AddRoleRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.Entity.Category;
import com.gentlemonster.Entity.Product;
import com.gentlemonster.Entity.ProductType;
import com.gentlemonster.Exception.DataNotFoundException;
import com.gentlemonster.Repository.ICategoryRepository;
import com.gentlemonster.Repository.IProductRepository;
import com.gentlemonster.Repository.IProductTypeRepository;
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
    private IProductTypeRepository iProductTypeRepository;
    @Autowired
    private LocalizationUtil localizationUtils;

    @Override
    public APIResponse<Product> createProduct(AddProductRequest addProductRequest) {
        Product productCreate = modelMapper.map(addProductRequest, Product.class);
        ProductType productType = iProductTypeRepository.findById(UUID.fromString(addProductRequest.getProductType()))
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKey.PRODUCT_TYPE_NOT_FOUND)));
        productCreate.setProductType(productType);
        iProductRepository.save(productCreate);
        List<String> messages = new ArrayList<>();
        messages.add(localizationUtils.getLocalizedMessage(MessageKey.PRODUCT_CREATE_SUCCESS));
        return new APIResponse<>(productCreate, messages);
    }
}
