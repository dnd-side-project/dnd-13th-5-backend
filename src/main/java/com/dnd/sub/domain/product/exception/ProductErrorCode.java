package com.dnd.sub.domain.product.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PF-401", "존재하지 않는 구독 서비스입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
