package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentTotalResponse;

import java.util.List;

public interface SubscriptionQueryRepository {
    List<GetMySubscriptionDto> findMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort);

    List<GetMySubscriptionDto> findMyFavorites(Long memberId, ProductCategoryType category, SubscriptionSortType sort);

    List<GetPaymentSoonDto> findPaymentSoon(Long memberId);

    GetPaymentTotalResponse findPaymentTotal(Long memberId);
}
