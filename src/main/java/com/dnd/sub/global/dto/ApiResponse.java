package com.dnd.sub.global.dto;

import com.dnd.sub.global.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;


public record ApiResponse<T>(
        int status,
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> ApiResponse<T> ok(String code,  T data) {
        return new ApiResponse<>(200, code, "정상적으로 처리 되었습니다.", data);
    }

    public static <T> ApiResponse<T> created(String code, T data) {
        return new ApiResponse<>(201, code,"정상적으로 생성되었습니다.", data);
    }

    public static <T> ApiResponse<T> fail(final ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getValue(), errorCode.getMessage(),errorCode.getMessage() ,null);
    }


}
