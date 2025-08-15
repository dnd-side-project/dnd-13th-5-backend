package com.dnd.sub.global.exception;

import com.dnd.sub.global.enums.GlobalErrorCode;
import com.dnd.sub.global.enums.TokenErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {
    private final GlobalErrorCode errorCode;


    public GlobalException(GlobalErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
