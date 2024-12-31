package com.gentlemonster.Browser.Admin.Controller;

import com.gentlemonster.Constant.Endpoint;
import com.gentlemonster.DTO.Request.ProductType.AddProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.EditProductTypeRequest;
import com.gentlemonster.DTO.Request.ProductType.ProductTypeRequest;
import com.gentlemonster.DTO.Response.PagingResponse;
import com.gentlemonster.Repository.IProductTypeRepository;
import com.gentlemonster.Service.ProductType.ProductTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Endpoint.ProductType.BASE)
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private IProductTypeRepository iProductTypeRepository;

    @GetMapping
    public ResponseEntity<PagingResponse<?>> getAllProductTypes(@ModelAttribute ProductTypeRequest productTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(new PagingResponse<>(null,  errorMessages, 0, (long) 0));
        }
        return ResponseEntity.ok(productTypeService.getProductType(productTypeRequest));
    }

    @PostMapping(Endpoint.ProductType.NEW)
    public ResponseEntity<?> createNewProductType(@Valid @RequestBody AddProductTypeRequest addProductTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(productTypeService.addType(addProductTypeRequest));
    }

    @PutMapping(Endpoint.ProductType.EDIT)
    public ResponseEntity<?> updateProductType(@PathVariable String productTypeID,@Valid @RequestBody EditProductTypeRequest editProductTypeRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            // Creating an APIResponse with error messages
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(productTypeService.updateType(productTypeID,editProductTypeRequest));
    }

    @DeleteMapping(Endpoint.ProductType.DELETE)
    public ResponseEntity<?> deleteProductType(@PathVariable String productTypeID) {
        return ResponseEntity.ok(productTypeService.deleteType(productTypeID));
    }
}
