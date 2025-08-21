package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubscriptionErrorCode implements ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "MF-401", "해당 유저(id)를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
