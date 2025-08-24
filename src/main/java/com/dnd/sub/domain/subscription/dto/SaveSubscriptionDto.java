package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveSubscriptionDto(
    Long productId,
    Long planId,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo,
    int participantCount
) {

    public static SaveSubscriptionDto from(SaveSubscriptionRequest request) {
        return new SaveSubscriptionDto(
            request.productId(),
            request.planId(),
            request.payCycleUnit(),
            request.startedAt(),
            request.paymentMethodId(),
            request.memo(),
            request.participantCount()
        );
    }
}
