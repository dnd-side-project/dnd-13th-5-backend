package com.dnd.sub.domain.paymentmethod.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PaymentMethodErrorCode implements ErrorCode {

    PAYMENT_METHOD_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PMF-401", "존재하지 않는 결제 수단입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
