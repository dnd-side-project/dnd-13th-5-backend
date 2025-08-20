package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

public record GetMySubscriptionDto(
    Long id,
    String name,
    ProductCategoryType category,
    int cycleNum,
    PayCycleUnitType cycleUnit,
    int price,
    boolean isFavorites,
    String imageUrl
) {
}
