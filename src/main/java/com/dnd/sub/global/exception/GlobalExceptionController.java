package com.dnd.sub.global.exception;

import com.dnd.sub.domain.auth.exception.TokenException;
import com.dnd.sub.domain.member.exception.MemberException;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GlobalException.class)
    protected ApiResponse<Void> handleGlobalException(GlobalException e){
        log.error("{} 발생!", e.getClass().getSimpleName(), e);
        return ApiResponse.fail(e.getErrorCode());
    }

}
