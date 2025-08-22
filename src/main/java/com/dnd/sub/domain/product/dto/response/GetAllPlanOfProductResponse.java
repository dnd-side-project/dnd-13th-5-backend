package com.dnd.sub.domain.product.dto.response;

import com.dnd.sub.domain.product.dto.GetAllPlanOfProductDto;

import java.util.List;

public record GetAllPlanOfProductResponse(
    List<GetAllPlanOfProductDto> plans
) {
}
