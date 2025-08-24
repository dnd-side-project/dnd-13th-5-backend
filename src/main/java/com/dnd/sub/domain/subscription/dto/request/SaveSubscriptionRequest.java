package com.dnd.sub.domain.subscription.dto.request;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record SaveSubscriptionRequest(
    Long productId,
    Long planId,
    int payCycleNum,
    PayCycleUnitType payCycleUnit,
    LocalDate startDay,
    Long paymentMethodId,
    String memo,
    int participantCount
) {
}
