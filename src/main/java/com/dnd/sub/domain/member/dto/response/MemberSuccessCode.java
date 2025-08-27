package com.dnd.sub.domain.member.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements SuccessCode {
    MEMBER_INFO_OK(HttpStatus.OK.value(), "MIS-201", "사용자 정보 조회 성공"),
    MEMBER_INFO_UPDATE(HttpStatus.OK.value(), "MIS-202", "이메일 변경 완료"),
    MEMBER_NOTI_UPDATE(HttpStatus.OK.value(), "MIS-203", "알람 상태 변경 완료"),
    MEMBER_WITHDRAW_OK(HttpStatus.OK.value(), "MIS-204", "회원탈퇴 완료")
    ;

    private final int status;
    private final String code;
    private final String message;

}
