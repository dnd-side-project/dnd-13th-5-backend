package com.dnd.sub.domain.product.dto;

public record GetAllPlanOfProductDto(
    Long id,
    String name,
    int price,
    String benefit
) {
}
