package com.dnd.sub.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    NOT_FOUND("UNF-401", HttpStatus.NOT_FOUND, "해당 유저(id)를 찾을 수 없습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
