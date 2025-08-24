package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubscriptionErrorCode implements ErrorCode {
    SUBSCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "SF-401", "존재하지 않는 구독 서비스입니다."),
    MEMBER_SUBSCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "SF-402", "사용자가 구독하지 않은 서비스입니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
