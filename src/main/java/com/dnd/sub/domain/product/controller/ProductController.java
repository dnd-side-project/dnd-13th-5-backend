package com.dnd.sub.domain.product.controller;

import com.dnd.sub.domain.product.service.ProductService;
import com.dnd.sub.domain.product.dto.response.GetProductCategoriesResponse;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dnd.sub.domain.product.dto.response.ProductSuccessCode.GET_PRODUCT_CATEGORIES;

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
}
