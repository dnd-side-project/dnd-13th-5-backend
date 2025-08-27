package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record GetMySubscriptionDetailInfoDto(
    Long id,
    String productName,
    ProductCategoryType category,
    String imageUrl,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    int totalPaymentCount,
    int price,
    String planName,
    Long planId,
    boolean isCustom,
    Long paymentMethodId,
    String memo,
    int participantCount,
    String benefit,
    boolean isFavorite
) {
}
