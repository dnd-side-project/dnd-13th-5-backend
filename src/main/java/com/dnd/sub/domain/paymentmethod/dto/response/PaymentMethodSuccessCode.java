package com.dnd.sub.domain.paymentmethod.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PaymentMethodSuccessCode implements SuccessCode {

    GET_ALL_PAYMENT_METHODS(HttpStatus.OK.value(), "PMS-201", "결제 수단 조회 성공"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
