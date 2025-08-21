package com.dnd.sub.domain.subscription.exception;

import lombok.Getter;

@Getter
public class SubscriptionException extends RuntimeException {

    private final SubscriptionErrorCode errorCode;

    public SubscriptionException(final SubscriptionErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
