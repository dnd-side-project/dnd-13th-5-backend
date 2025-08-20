package com.dnd.sub.domain.subscription.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubscriptionSuccessCode implements SuccessCode {
    GET_MY_SUBSCRIPTIONS("SS-201", HttpStatus.OK, "내 구독 서비스 전체 조회 성공"),
    ;

    private final String code;
    private final HttpStatus status;
    private final String message;

}
