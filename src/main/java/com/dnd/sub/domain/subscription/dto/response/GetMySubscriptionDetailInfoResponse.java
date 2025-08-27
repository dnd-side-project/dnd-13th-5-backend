package com.dnd.sub.domain.subscription.dto.response;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDetailInfoDto;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;

import java.time.LocalDate;

public record GetMySubscriptionDetailInfoResponse(
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

    public static GetMySubscriptionDetailInfoResponse from(GetMySubscriptionDetailInfoDto dto) {
        return new GetMySubscriptionDetailInfoResponse(
            dto.id(),
            dto.productName(),
            dto.category(),
            dto.imageUrl(),
            dto.payCycleUnit(),
            dto.startedAt(),
            dto.totalPaymentCount(),
            dto.price(),
            dto.planName(),
            dto.productId(),
            dto.isCustom(),
            dto.paymentMethodId(),
            dto.memo(),
            dto.participantCount(),
            dto.benefit(),
            dto.isFavorite()
        );
    }
}
