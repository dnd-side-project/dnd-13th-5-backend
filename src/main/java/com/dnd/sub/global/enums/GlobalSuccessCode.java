package com.dnd.sub.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalSuccessCode {

    OK(HttpStatus.OK, "exampleCode", "응답 성공"),
    CREATED(HttpStatus.CREATED, "exampleCode", "생성 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;


}
