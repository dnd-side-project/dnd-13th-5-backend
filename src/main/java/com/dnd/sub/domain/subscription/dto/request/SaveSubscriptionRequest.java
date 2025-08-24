package com.dnd.sub.domain.subscription.dto.request;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveSubscriptionRequest(
    Long productId,
    Long planId,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo,
    int participantCount
) {
}
