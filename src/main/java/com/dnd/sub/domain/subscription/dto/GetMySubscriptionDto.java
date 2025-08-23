package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

public record GetMySubscriptionDto(
    Long id,
    String name,
    ProductCategoryType category,
    int payCycleNum,
    PayCycleUnitType payCycleUnit,
    String planName,
    int price,
    boolean isFavorites,
    String imageUrl
) {
}
