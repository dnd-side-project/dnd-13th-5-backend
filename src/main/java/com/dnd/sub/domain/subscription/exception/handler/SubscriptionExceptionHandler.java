package com.dnd.sub.domain.subscription.exception.handler;

import com.dnd.sub.domain.subscription.exception.SubscriptionException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class SubscriptionExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SubscriptionException.class)
    public ApiResponse<Void> handleSubscriptionException(final SubscriptionException e) {
        log.error("{} 발생!", e.getClass().getSimpleName(), e);
        return ApiResponse.fail(e.getErrorCode());
    }
}
