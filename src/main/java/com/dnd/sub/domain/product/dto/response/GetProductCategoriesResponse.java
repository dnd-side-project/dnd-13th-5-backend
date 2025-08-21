package com.dnd.sub.domain.product.dto.response;

import java.util.List;

public record GetProductCategoriesResponse(
    List<String> categories
) {
}
