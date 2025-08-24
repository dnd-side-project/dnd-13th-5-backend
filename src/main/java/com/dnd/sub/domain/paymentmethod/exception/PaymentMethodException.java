package com.dnd.sub.domain.paymentmethod.exception;

import lombok.Getter;

@Getter
public class PaymentMethodException extends RuntimeException {

    private final PaymentMethodErrorCode errorCode;

    public PaymentMethodException(final PaymentMethodErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
