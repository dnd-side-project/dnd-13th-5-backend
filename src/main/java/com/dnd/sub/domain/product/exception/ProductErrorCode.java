package com.dnd.sub.domain.product.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {
    ;

    private final int status;
    private final String code;
    private final String message;
}
