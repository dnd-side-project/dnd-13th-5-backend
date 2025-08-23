package com.dnd.sub.domain.subscription.dto;

import java.time.LocalDate;

public record GetPaymentSoonDto(
        Long id,
        String name,
        int price,
        LocalDate nextDueDate
) {
}
