package com.dnd.sub.global.dto;

import com.dnd.sub.global.enums.GlobalErrorCode;
import com.dnd.sub.global.enums.GlobalSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


public record ApiResponse<T>(
        HttpStatus status,
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> ApiResponse<T> ok(GlobalSuccessCode code, T data) {
        return new ApiResponse<>(code.getStatus(), code.getCode(), code.getMessage(), data);
    }

    public static <T> ApiResponse<T> created(GlobalSuccessCode code, T data) {
        return new ApiResponse<>(code.getStatus(), code.getCode(), code.getMessage(), data);
    }

    public static <T> ApiResponse<T> fail(GlobalErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getHttpStatus(), errorCode.getCode(), errorCode.getMessage() ,null);
    }


}
