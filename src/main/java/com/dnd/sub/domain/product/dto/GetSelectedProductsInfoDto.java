package com.dnd.sub.domain.product.dto;

import java.util.List;

public record GetSelectedProductsInfoDto(
    Long id,
    String name,
    String imageUrl,
    List<ProductPlans> plans
) {

    public record ProductPlans(
        Long planId,
        String planName,
        String benefit
    ) {
    }
}
