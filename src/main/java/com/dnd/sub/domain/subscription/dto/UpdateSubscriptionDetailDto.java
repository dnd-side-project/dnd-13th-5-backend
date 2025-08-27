package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.subscription.dto.request.UpdateSubscriptionDetailRequest;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;
import java.util.Optional;

public record UpdateSubscriptionDetailDto(
    Optional<Long> planId,
    Optional<String> productName,
    Optional<Integer> price,
    int participantCount,
    PayCycleUnitType payCycleUnit,
    LocalDate startedAt,
    Long paymentMethodId,
    String memo
) {

    public static UpdateSubscriptionDetailDto from(UpdateSubscriptionDetailRequest request) {
        return new UpdateSubscriptionDetailDto(
            request.planId(),
            request.productName(),
            request.price(),
            request.participantCount(),
            request.payCycleUnit(),
            request.startedAt(),
            request.paymentMethodId(),
            request.memo()
        );
    }
}
