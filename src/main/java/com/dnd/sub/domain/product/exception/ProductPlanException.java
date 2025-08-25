package com.dnd.sub.domain.product.exception;

import lombok.Getter;

@Getter
public class ProductPlanException extends RuntimeException {

    private final ProductPlanErrorCode errorCode;

    public ProductPlanException(final ProductPlanErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
