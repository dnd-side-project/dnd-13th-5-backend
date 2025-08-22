package com.dnd.sub.domain.subscription.service;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<GetMySubscriptionDto> getMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {
        return subscriptionRepository.findMySubscriptions(memberId, category, sort);
    }

    public List<GetMySubscriptionDto> getMyFavorites(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {
        return subscriptionRepository.findMyFavorites(memberId, category, sort);
    }
}
