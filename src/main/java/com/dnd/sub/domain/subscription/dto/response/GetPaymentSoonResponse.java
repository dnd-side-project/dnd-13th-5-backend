package com.dnd.sub.domain.subscription.dto.response;

import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;

import java.util.List;

public record GetPaymentSoonResponse(
        List<GetPaymentSoonDto> services
) {
}
