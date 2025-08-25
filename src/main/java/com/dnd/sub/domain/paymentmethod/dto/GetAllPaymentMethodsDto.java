package com.dnd.sub.domain.paymentmethod.dto;

import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;

import java.util.List;

public record GetAllPaymentMethodsDto(
    List<PaymentMethod> card,
    List<PaymentMethod> account,
    List<PaymentMethod> easyPay
) {
}
