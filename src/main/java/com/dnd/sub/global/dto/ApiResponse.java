package com.dnd.sub.global.dto;

import com.dnd.sub.global.enums.ErrorCode;
import com.dnd.sub.global.enums.GlobalErrorCode;
import com.dnd.sub.global.enums.GlobalSuccessCode;
import com.dnd.sub.global.enums.SuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


public record ApiResponse<T>(
        HttpStatus status,
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static ApiResponse<Void> success(final SuccessCode code) {
        return new ApiResponse<>(code.getStatus(), code.getCode(), code.getMessage(), null);
    }

    public static <T> ApiResponse<T> success(final SuccessCode code, T data) {
        return new ApiResponse<>(code.getStatus(), code.getCode(), code.getMessage(), data);
    }

    public static <T> ApiResponse<T> fail(final ErrorCode code) {
        return new ApiResponse<>(code.getStatus(), code.getCode(), code.getMessage() ,null);
    }


}
