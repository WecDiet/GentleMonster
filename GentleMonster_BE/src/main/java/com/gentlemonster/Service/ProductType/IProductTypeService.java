package com.gentlemonster.Service.ProductType;

import com.gentlemonster.DTO.Request.ProductType.AddProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.EditProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.ProductTypeRequest;
import com.gentlemonster.DTO.Response.APIResponse;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.DTO.Response.ProductType.ProductTypeResponse;
import com.gentlemonster.Entity.ProductType;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface IProductTypeService {
    PagingResponse<List<ProductTypeResponse>> getProductType(@ModelAttribute ProductTypeRequest productTypeRequest);
    APIResponse<ProductType> addType(AddProductTypeRequest addProductTypeRequest);
    APIResponse<ProductType> updateType(String productTypeID,EditProductTypeRequest editProductTypeRequest);
    APIResponse<Boolean> deleteType(String productTypeID);
}
