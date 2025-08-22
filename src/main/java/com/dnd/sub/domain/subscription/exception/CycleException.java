package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;

@Getter
public class CycleException extends RuntimeException {
    private final CycleErrorCode errorCode;

    public CycleException(final CycleErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
