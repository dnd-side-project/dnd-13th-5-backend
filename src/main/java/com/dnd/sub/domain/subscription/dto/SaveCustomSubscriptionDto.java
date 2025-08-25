package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.dto.request.SaveCustomSubscriptionRequest;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveCustomSubscriptionDto(
    String productName,
    ProductCategoryType category,
    int price,
    int participantCount,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo
) {

    public static SaveCustomSubscriptionDto from(SaveCustomSubscriptionRequest request) {
        return new SaveCustomSubscriptionDto(
            request.productName(),
            request.category(),
            request.price(),
            request.participantCount(),
            request.payCycleUnit(),
            request.startedAt(),
            request.paymentMethodId(),
            request.memo()
        );
    }
}
