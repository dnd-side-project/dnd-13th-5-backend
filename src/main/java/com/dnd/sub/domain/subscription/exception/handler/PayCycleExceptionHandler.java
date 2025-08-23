package com.dnd.sub.domain.subscription.exception.handler;

import com.dnd.sub.domain.subscription.exception.PayCycleException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.dnd.sub.global.dto.ApiResponse.fail;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class PayCycleExceptionHandler {

    @ExceptionHandler(PayCycleException.class)
    public ResponseEntity<ApiResponse<Void>> handlePayCycleException(final PayCycleException e) {
        log.error("{} 발생!", e.getClass().getSimpleName(), e);
        return ResponseEntity.badRequest().body(fail(e.getErrorCode()));
    }
}
