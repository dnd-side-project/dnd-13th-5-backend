package com.dnd.sub.domain.subscription.exception;

import lombok.Getter;

@Getter
public class PayCycleException extends RuntimeException {
    private final PayCycleErrorCode errorCode;

    public PayCycleException(final PayCycleErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
