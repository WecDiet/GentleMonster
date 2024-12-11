package com.gentlemonster.Service.Product;

import com.gentlemonster.DTO.Request.Product.AddProductRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.Entity.Product;

public interface IProductService {
    APIResponse<Product> createProduct(AddProductRequest addProductRequest);
}
