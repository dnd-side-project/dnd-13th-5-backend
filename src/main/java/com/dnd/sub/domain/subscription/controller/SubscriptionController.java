package com.dnd.sub.domain.subscription.controller;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.response.GetMySubscriptionsResponse;
import com.dnd.sub.domain.subscription.service.SubscriptionService;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dnd.sub.domain.subscription.dto.response.SubscriptionSuccessCode.GET_MY_FAVORITES;
import static com.dnd.sub.domain.subscription.dto.response.SubscriptionSuccessCode.GET_MY_SUBSCRIPTIONS;

@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/my")
    public ApiResponse<GetMySubscriptionsResponse> getMySubscriptions(
        @AuthenticationPrincipal Long memberId,
        @RequestParam(required = false) ProductCategoryType category,
        @RequestParam(required = false) SubscriptionSortType sort
    ) {
        List<GetMySubscriptionDto> mySubscriptions = subscriptionService.getMySubscriptions(memberId, category, sort);
        GetMySubscriptionsResponse response = new GetMySubscriptionsResponse(mySubscriptions);

        return ApiResponse.success(GET_MY_SUBSCRIPTIONS, response);
    }

    @GetMapping("/my/favorites")
    public ApiResponse<GetMySubscriptionsResponse> getMyFavorites(
        @AuthenticationPrincipal Long memberId,
        @RequestParam(required = false) ProductCategoryType category,
        @RequestParam(required = false) SubscriptionSortType sort
    ) {
        List<GetMySubscriptionDto> myFavorites = subscriptionService.getMyFavorites(memberId, category, sort);
        GetMySubscriptionsResponse response = new GetMySubscriptionsResponse(myFavorites);

        return ApiResponse.success(GET_MY_FAVORITES, response);
    }
}
