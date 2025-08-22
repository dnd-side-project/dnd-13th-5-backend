package com.dnd.sub.domain.auth.exception.handler;

import com.dnd.sub.domain.auth.exception.TokenException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(TokenException.class)
    protected ApiResponse<Void> handleTokenException(final TokenException e) {
        log.error("TokenException: {}", e.getErrorCode().getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }
}
