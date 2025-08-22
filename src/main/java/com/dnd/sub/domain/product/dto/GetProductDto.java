package com.dnd.sub.domain.product.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;

public record GetProductDto(
    Long productId,
    String name,
    ProductCategoryType category,
    String imageUrl,
    int minPrice,
    int maxPrice
) {
}
