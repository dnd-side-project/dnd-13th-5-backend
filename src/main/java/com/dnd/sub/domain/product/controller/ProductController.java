package com.dnd.sub.domain.product.controller;

import com.dnd.sub.domain.product.dto.GetAllPlanOfProductDto;
import com.dnd.sub.domain.product.dto.GetProductDto;
import com.dnd.sub.domain.product.dto.response.GetAllPlanOfProductResponse;
import com.dnd.sub.domain.product.dto.response.GetAllProductsResponse;
import com.dnd.sub.domain.product.dto.response.GetProductCategoriesResponse;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.service.ProductService;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dnd.sub.domain.product.dto.response.ProductSuccessCode.*;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    @GetMapping("/categories")
    public ApiResponse<GetProductCategoriesResponse> getProductCategories() {
        List<String> categories = productService.getProductCategories();
        GetProductCategoriesResponse response = new GetProductCategoriesResponse(categories);

        return ApiResponse.success(GET_PRODUCT_CATEGORIES, response);
    }

    @GetMapping
    public ApiResponse<GetAllProductsResponse> getAllProducts(@RequestParam(required = false) ProductCategoryType category) {
        List<GetProductDto> products = productService.getAllProducts(category);
        GetAllProductsResponse response = GetAllProductsResponse.from(products);

        return ApiResponse.success(GET_ALL_PRODUCTS, response);
    }

    @GetMapping("/{productId}/plans")
    public ApiResponse<GetAllPlanOfProductResponse> getAllPlanOfProduct(@PathVariable Long productId) {
        List<GetAllPlanOfProductDto> plans = productService.getAllPlanOfProduct(productId);
        GetAllPlanOfProductResponse response = new GetAllPlanOfProductResponse(plans);

        return ApiResponse.success(GET_ALL_PLAN_OF_PRODUCT, response);
    }
}
