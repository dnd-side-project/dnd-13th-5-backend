package com.dnd.sub.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        HttpStatus status,
        String code,
        String Message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data
) {
    public static <T> ApiResponse<T> ok(HttpStatus status, String code, String message, T data) {
        return new ApiResponse<>(status, code, message, data);
    }

}
