package com.dnd.sub.domain.product.dto.response;

import com.dnd.sub.global.enums.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProductSuccessCode implements SuccessCode {
    GET_PRODUCT_CATEGORIES(HttpStatus.OK.value(), "PS-201", "구독 카테고리 목록 조회 성공"),
    GET_ALL_PRODUCTS(HttpStatus.OK.value(), "PS-202", "구독 서비스 전체 조회 성공"),
    GET_ALL_PLAN_OF_PRODUCT(HttpStatus.OK.value(), "PS-203", "구독 서비스 별 요금제 목록 조회 성공"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
