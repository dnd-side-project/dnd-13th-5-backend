package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CycleErrorCode implements ErrorCode {
    CYCLE_TYPE_ERROR(HttpStatus.BAD_REQUEST.value(), "CF-400", "잘못된 결제 주기입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
