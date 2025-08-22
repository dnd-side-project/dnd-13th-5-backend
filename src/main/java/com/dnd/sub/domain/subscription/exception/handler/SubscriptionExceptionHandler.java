package com.dnd.sub.domain.subscription.exception.handler;

import com.dnd.sub.domain.subscription.exception.SubscriptionException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.dnd.sub.domain.subscription.exception.SubscriptionErrorCode.SUBSCRIPTION_ERROR;
import static com.dnd.sub.global.dto.ApiResponse.fail;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class SubscriptionExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse<Void>> handleMemberActivityException(final SubscriptionException e) {
        log.error("{} 발생!", e.getClass().getSimpleName(), e);
        return ResponseEntity.badRequest()
            .body(fail(SUBSCRIPTION_ERROR));
    }
}
