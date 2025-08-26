package com.dnd.sub.domain.subscription.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubscriptionSuccessCode implements SuccessCode {
    GET_MY_SUBSCRIPTIONS(HttpStatus.OK.value(), "SS-201", "내 구독 서비스 전체 조회 성공"),
    GET_MY_FAVORITES(HttpStatus.OK.value(), "SS-202", "내 즐겨찾기 전체 조회 성공"),
    GET_PAYMENT_SOON(HttpStatus.OK.value(), "SS-203", "결제 임박 서비스 조회 성공"),
    SAVE_SUBSCRIPTION(HttpStatus.CREATED.value(), "SS-204", "구독 등록 성공"),
    UPDATE_IS_FAVORITE(HttpStatus.OK.value(), "SS-205", "즐겨찾기 추가/해지 성공"),
    SAVE_CUSTOM_SUBSCRIPTION(HttpStatus.CREATED.value(), "SS-206", "커스텀 구독 등록 성공"),
    GET_MY_SUBSCRIPTION_DETAIL_INFO(HttpStatus.OK.value(), "SS-207", "내 구독 상세 정보 조회 성공"),
    GET_UNSUBSCRIBE_URL(HttpStatus.OK.value(), "SS-208", "해지 링크 조회 성공"),
    GET_PAYMENT_TOTAL(HttpStatus.OK.value(), "SS-211", "월간 결제 요약 조회 성공"),
    ;

    private final int status;
    private final String code;
    private final String message;

}
