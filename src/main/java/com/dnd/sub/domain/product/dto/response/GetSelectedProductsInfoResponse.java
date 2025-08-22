package com.dnd.sub.domain.product.dto.response;

import com.dnd.sub.domain.product.dto.GetSelectedProductsInfoDto;

import java.util.List;

public record GetSelectedProductsInfoResponse(
    List<GetSelectedProductsInfoDto> products
) {
}
