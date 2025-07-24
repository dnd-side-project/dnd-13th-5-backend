package com.dnd.sub.global.exception;

import com.dnd.sub.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GlobalException.class)
    protected ApiResponse<Void> handleGlobalException(GlobalException e){
        log.error("GlobalException: {}", e.getErrorCode().getMessage());
        return ApiResponse.fail(e.getErrorCode());
    }

}
