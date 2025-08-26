package com.dnd.sub.domain.product.dto.response;

import com.dnd.sub.domain.product.dto.GetProductDto;

import java.util.List;

public record GetRandomProductsRecommendationResponse(
    List<GetProductDto> products
) {
}
