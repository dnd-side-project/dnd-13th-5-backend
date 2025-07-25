package com.dnd.sub.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404, HttpStatus.NOT_FOUND, "값을 찾을 수 없습니다.");


    private final int value;
    private final HttpStatus httpStatus;
    private final String message;
}
