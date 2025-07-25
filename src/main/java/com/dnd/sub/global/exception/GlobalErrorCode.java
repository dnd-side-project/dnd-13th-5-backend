package com.dnd.sub.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {
    NOT_FOUND("임시 코드", HttpStatus.NOT_FOUND, "값을 찾을 수 없습니다.");


    private final String code;
    private final HttpStatus httpStatus;
    private final String message;
}
