package com.dnd.sub.domain.product.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {

    PRODUCT_ERROR(HttpStatus.BAD_REQUEST.value(), "PF-401", "구독 서비스에 예기치 못한 문제가 발생했습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
