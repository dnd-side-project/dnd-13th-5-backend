package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubscriptionErrorCode implements ErrorCode {
    SUBSCRIPTION_ERROR(HttpStatus.BAD_REQUEST.value(), "SF-401", "사용자가 구독중인 서비스에 예기치 못한 문제가 발생했습니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
