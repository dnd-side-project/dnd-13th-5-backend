package com.dnd.sub.domain.subscription.dto.request;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveCustomSubscriptionRequest(
    String productName,
    ProductCategoryType category,
    int price,
    int participantCount,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo
) {
}
