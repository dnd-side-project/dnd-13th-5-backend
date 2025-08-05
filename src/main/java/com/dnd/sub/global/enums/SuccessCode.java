package com.dnd.sub.global.enums;

import org.springframework.http.HttpStatus;

public interface SuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
