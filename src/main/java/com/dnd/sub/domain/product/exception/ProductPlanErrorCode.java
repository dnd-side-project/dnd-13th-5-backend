package com.dnd.sub.domain.product.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductPlanErrorCode implements ErrorCode {
    PRODUCT_PLAN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PP-401", "존재하지 않는 요금제입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
