package com.dnd.sub.domain.auth.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessCode implements SuccessCode {
    REISSUE_OK(200, "AS-202", "토큰 재발급 성공"),
    LOGOUT_OK(200, "AS-203", "로그아웃 성공")
    ;

    private final int status;
    private final String code;
    private final String message;
}
