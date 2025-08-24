package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record GetMySubscriptionDto(
    Long id,
    String name,
    ProductCategoryType category,
    PayCycleUnitType payCycleUnit,
    String planName,
    int price,
    boolean isFavorites,
    String imageUrl,
    LocalDate startedAt
) {
}
