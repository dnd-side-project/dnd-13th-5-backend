package com.dnd.sub.global.exception;

import com.dnd.sub.global.enums.GlobalErrorCode;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final GlobalErrorCode errorCode;

    public GlobalException(GlobalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
