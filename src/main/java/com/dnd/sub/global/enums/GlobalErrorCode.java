package com.dnd.sub.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "임시 코드", "값을 찾을 수 없습니다.");


    private final int status;
    private final String code;
    private final String message;
}
