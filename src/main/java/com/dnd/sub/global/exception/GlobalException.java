package com.dnd.sub.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;
}
