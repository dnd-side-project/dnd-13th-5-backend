package com.dnd.sub.domain.paymentmethod.dto.response;

import com.dnd.sub.domain.paymentmethod.dto.GetAllPaymentMethodsDto;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;

import java.util.List;

public record GetAllPaymentMethodsResponse(
    List<PaymentMethod> card,
    List<PaymentMethod> account,
    List<PaymentMethod> easyPay

) {

    public static GetAllPaymentMethodsResponse from(GetAllPaymentMethodsDto dto) {
        return new GetAllPaymentMethodsResponse(
            dto.card(),
            dto.account(),
            dto.easyPay()
        );
    }
}
