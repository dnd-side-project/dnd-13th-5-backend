package com.dnd.sub.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategoryType {
    OTT("OTT"),
    SHOPPING("쇼핑"),
    MUSIC("음악"),
    CLOUD("클라우드"),
    AI("AI"),
    PRODUCTIVITY("생산성"),
    EDUCATION("교육"),
    DELIVERY("배달");

    private final String category;
}
