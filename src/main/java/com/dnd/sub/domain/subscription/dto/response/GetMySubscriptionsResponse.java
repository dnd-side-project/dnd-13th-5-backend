package com.dnd.sub.domain.subscription.dto.response;

import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;

import java.util.List;

public record GetMySubscriptionsResponse(
    List<GetMySubscriptionDto> services
) {
}
