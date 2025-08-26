package com.dnd.sub.domain.product.controller;

import com.dnd.sub.domain.product.dto.GetAllPlanOfProductDto;
import com.dnd.sub.domain.product.dto.GetProductDto;
import com.dnd.sub.domain.product.dto.GetSelectedProductsInfoDto;
import com.dnd.sub.domain.product.dto.response.GetAllPlanOfProductResponse;
import com.dnd.sub.domain.product.dto.response.GetAllProductsResponse;
import com.dnd.sub.domain.product.dto.response.GetProductCategoriesResponse;
import com.dnd.sub.domain.product.dto.response.GetSelectedProductsInfoResponse;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.service.ProductService;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
public class ProductController implements ProductControllerDocs {

    private final ProductService productService;

    @GetMapping
    public ApiResponse<GetAllProductsResponse> getAllProducts(@RequestParam(required = false) ProductCategoryType category) {
        List<GetProductDto> products = productService.getAllProducts(category);
        GetAllProductsResponse response = new GetAllProductsResponse(products);

        return ApiResponse.success(GET_ALL_PRODUCTS, response);
    }

    @GetMapping("/recommendations")
    public ApiResponse<GetAllProductsResponse> getRandomProductsRecommendation(@AuthenticationPrincipal Long memberId, @RequestParam(required = false) ProductCategoryType category) {
        List<GetProductDto> products = productService.getRandomProductsRecommendation(memberId, category);
        GetAllProductsResponse response = new GetAllProductsResponse(products);

        return ApiResponse.success(GET_RANDOM_PRODUCTS_RECOMMENDATION, response);
    }

    @GetMapping("/info")
    public ApiResponse<GetSelectedProductsInfoResponse> getSelectedProductsInfo(@RequestParam List<Long> productIds) {
        List<GetSelectedProductsInfoDto> products = productService.getSelectedProductsInfo(productIds);
        GetSelectedProductsInfoResponse response = new GetSelectedProductsInfoResponse(products);

        return ApiResponse.success(GET_SELECTED_PRODUCTS_INFO, response);
    }

    @GetMapping("/categories")
    public ApiResponse<GetProductCategoriesResponse> getProductCategories() {
        List<String> categories = productService.getProductCategories();
        GetProductCategoriesResponse response = new GetProductCategoriesResponse(categories);

        return ApiResponse.success(GET_PRODUCT_CATEGORIES, response);
    }

    @GetMapping("/{productId}/plans")
    public ApiResponse<GetAllPlanOfProductResponse> getAllPlanOfProduct(@PathVariable Long productId) {
        List<GetAllPlanOfProductDto> plans = productService.getAllPlanOfProduct(productId);
        GetAllPlanOfProductResponse response = new GetAllPlanOfProductResponse(plans);

        return ApiResponse.success(GET_ALL_PLAN_OF_PRODUCT, response);
    }
}
