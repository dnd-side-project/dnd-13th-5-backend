package com.dnd.sub.domain.subscription.dto.response;

public record GetPaymentTotalResponse(
        String userName,
        int totalAmount,
        int remainingAmount,
        int progressPercent,
        int subCount
){
}
