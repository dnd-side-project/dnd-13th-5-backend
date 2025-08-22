package com.dnd.sub.domain.product.dto.response;

import com.dnd.sub.domain.product.dto.GetProductDto;

import java.util.List;

public record GetAllProductsResponse(
    List<ProductResponse> products
) {

    public static GetAllProductsResponse from(final List<GetProductDto> dtos) {
        List<ProductResponse> products = dtos.stream()
            .map(ProductResponse::from)
            .toList();
        return new GetAllProductsResponse(products);
    }

    public record ProductResponse(
        Long productId,
        String name,
        String category,
        String imageUrl,
        String priceRange
    ) {
        public static ProductResponse from(final GetProductDto dto) {
            return new ProductResponse(
                dto.productId(),
                dto.name(),
                dto.category().name(),
                dto.imageUrl(),
                formatPriceRange(dto.minPrice(), dto.maxPrice())
            );
        }

        private static String formatPriceRange(final int min, final int max) {
            if (min == 0 && max == 0) {
                return null;
            }
            if (min == max) {
                return String.format("월 %,d원", min);
            }
            return String.format("월 %,d원 ~ 월 %,d원", min, max);
        }
    }
}
