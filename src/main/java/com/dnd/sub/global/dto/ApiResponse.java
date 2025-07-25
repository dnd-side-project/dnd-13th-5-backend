package com.dnd.sub.global.dto;

import com.dnd.sub.global.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


public record ApiResponse<T>(
        HttpStatus status,
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> ApiResponse<T> ok(String code,  T data) {
        return new ApiResponse<>(HttpStatus.OK, code, "정상적으로 처리 되었습니다.", data);
    }

    public static <T> ApiResponse<T> created(String code, T data) {
        return new ApiResponse<>(HttpStatus.CREATED, code,"정상적으로 생성되었습니다.", data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getHttpStatus(), errorCode.getMessage(),errorCode.getMessage() ,null);
    }


}
