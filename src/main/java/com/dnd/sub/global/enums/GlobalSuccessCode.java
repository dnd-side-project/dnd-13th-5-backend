package com.dnd.sub.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GlobalSuccessCode implements SuccessCode{

    OK(HttpStatus.OK.value(), "success", "응답 성공"),
    CREATED(HttpStatus.CREATED.value(), "exampleCode", "생성 성공");

    private final int status;
    private final String code;
    private final String message;


}
