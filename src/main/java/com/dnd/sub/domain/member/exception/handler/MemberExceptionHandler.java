package com.dnd.sub.domain.member.exception.handler;

import com.dnd.sub.domain.member.exception.MemberException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(MemberException.class)
    protected ApiResponse<Void> handleMemberException(final MemberException e) {
        log.error("MemberException: {}", e.getErrorCode().getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }
}
