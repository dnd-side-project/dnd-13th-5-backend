package com.dnd.sub.domain.product.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductErrorCode errorCode;

    public ProductException(final ProductErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
