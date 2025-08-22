package com.dnd.sub.domain.subscription.dto;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record GetPaymentSoonDto(
        Long id,
        String name,
        int price,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate nextDueDate
) {
}
