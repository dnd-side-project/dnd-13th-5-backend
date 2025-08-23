package com.dnd.sub.domain.subscription.exception;

import lombok.Getter;

@Getter
public class CycleException extends RuntimeException {
    private final PayCycleErrorCode errorCode;

    public CycleException(final PayCycleErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
