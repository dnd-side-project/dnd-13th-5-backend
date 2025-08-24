package com.dnd.sub.domain.subscription.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionErrorCode implements ErrorCode {
    ;

    private final int status;
    private final String code;
    private final String message;
}
