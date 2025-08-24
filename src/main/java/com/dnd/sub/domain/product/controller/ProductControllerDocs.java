package com.dnd.sub.domain.product.controller;

import com.dnd.sub.domain.product.dto.GetAllPlanOfProductDto;
import com.dnd.sub.domain.product.dto.GetProductDto;
import com.dnd.sub.domain.product.dto.GetSelectedProductsInfoDto;
import com.dnd.sub.domain.product.dto.response.GetAllPlanOfProductResponse;
import com.dnd.sub.domain.product.dto.response.GetAllProductsResponse;
import com.dnd.sub.domain.product.dto.response.GetProductCategoriesResponse;
import com.dnd.sub.domain.product.dto.response.GetSelectedProductsInfoResponse;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.dnd.sub.domain.product.dto.response.ProductSuccessCode.*;
import static com.dnd.sub.domain.product.dto.response.ProductSuccessCode.GET_ALL_PLAN_OF_PRODUCT;

@Tag(name = "구독 상품 API", description = "구독 상품과 관련한 API입니다.")
public interface ProductControllerDocs {

    @Operation(
            summary = "카테고리 별 구독 서비스 전체 조회",
            description = "카테고리별 구독 서비스 목록을 조회합니다."
    )
    @GetMapping
    public ApiResponse<GetAllProductsResponse> getAllProducts(@RequestParam(required = false) ProductCategoryType category);

    @Operation(
            summary = "선택한 구독 서비스둘 정보 조회",
            description = "선택한 구독 서비스들의 정보를 조회합니다.."
    )
    @GetMapping("/info")
    public ApiResponse<GetSelectedProductsInfoResponse> getSelectedProductsInfo(@RequestParam List<Long> productIds);

    @Operation(
            summary = "구독 카테고리 목록 조회",
            description = "상품 카테고리 목록을 조회합니다."
    )
    @GetMapping("/categories")
    public ApiResponse<GetProductCategoriesResponse> getProductCategories();

    @Operation(
            summary = "각 구독 서비스 별 요금제 목록 조회",
            description = "각 구독 서비스 별 요금제 목록 조회 목록을 조회합니다."
    )
    @GetMapping("/{productId}/plans")
    public ApiResponse<GetAllPlanOfProductResponse> getAllPlanOfProduct(@PathVariable Long productId);
}
