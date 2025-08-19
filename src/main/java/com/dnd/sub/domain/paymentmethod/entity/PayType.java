package com.dnd.sub.domain.paymentmethod.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayType {
    CARD,
    ACCOUNT,
    EASY_PAY
    ;
}
