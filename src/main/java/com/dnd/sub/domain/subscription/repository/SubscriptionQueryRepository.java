package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;

import java.util.List;

public interface SubscriptionQueryRepository {
    List<GetMySubscriptionDto> findMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort);
}
