package com.dnd.sub.domain.subscription.dto.request;

import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateSubscriptionDetailRequest(

    Optional<Long> planId,
    Optional<String> productName,
    Optional<Integer> price,
    int participantCount,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo
) {
}
