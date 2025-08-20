package com.dnd.sub.domain.member.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements SuccessCode {
    MEMBER_INFO_OK("MIS-201", HttpStatus.OK, "사용자 정보 조회 성공"),
    MEMBER_INFO_UPDATE("MIS-202", HttpStatus.OK, "이메일 변경 완료"),
    MEMBER_NOTI_UPDATE("MIS-203", HttpStatus.OK, "알람 상태 변경 완료");

    private final String code;
    private final HttpStatus status;
    private final String message;

}
