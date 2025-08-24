package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveSubscriptionDto(
    Long productId,
    Long planId,
    int payCycleNum,
    PayCycleUnitType payCycleUnit,
    LocalDate startDay,
    Long paymentMethodId,
    String memo,
    int participantCount
) {

    public static SaveSubscriptionDto from(SaveSubscriptionRequest request) {
        return new SaveSubscriptionDto(
            request.productId(),
            request.planId(),
            request.payCycleNum(),
            request.payCycleUnit(),
            request.startDay(),
            request.paymentMethodId(),
            request.memo(),
            request.participantCount()
        );
    }
}
