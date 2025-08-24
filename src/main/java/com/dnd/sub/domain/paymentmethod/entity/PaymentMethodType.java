package com.dnd.sub.domain.paymentmethod.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentMethodType {
    CARD,
    ACCOUNT,
    EASY_PAY
    ;
}
