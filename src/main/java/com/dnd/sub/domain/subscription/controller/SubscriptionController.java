package com.dnd.sub.domain.subscription.controller;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDetailInfoDto;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;
import com.dnd.sub.domain.subscription.dto.SaveCustomSubscriptionDto;
import com.dnd.sub.domain.subscription.dto.SaveSubscriptionDto;
import com.dnd.sub.domain.subscription.dto.request.SaveCustomSubscriptionRequest;
import com.dnd.sub.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.dnd.sub.domain.subscription.dto.response.GetMySubscriptionDetailInfoResponse;
import com.dnd.sub.domain.subscription.dto.response.GetMySubscriptionsResponse;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentSoonResponse;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentTotalResponse;
import com.dnd.sub.domain.subscription.service.SubscriptionService;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dnd.sub.domain.subscription.dto.response.SubscriptionSuccessCode.*;

@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
@RestController
public class SubscriptionController implements SubscriptionControllerDocs {

    private final SubscriptionService subscriptionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<Void> saveSubscription(@AuthenticationPrincipal Long memberId, @RequestBody SaveSubscriptionRequest request) {
        SaveSubscriptionDto dto = SaveSubscriptionDto.from(request);
        subscriptionService.saveSubscription(memberId, dto);

        return ApiResponse.success(SAVE_SUBSCRIPTION);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/custom")
    public ApiResponse<Void> saveCustomSubscription(@AuthenticationPrincipal Long memberId, @RequestBody SaveCustomSubscriptionRequest request) {
        SaveCustomSubscriptionDto dto = SaveCustomSubscriptionDto.from(request);
        subscriptionService.saveCustomSubscription(memberId, dto);

        return ApiResponse.success(SAVE_CUSTOM_SUBSCRIPTION);
    }

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

    @GetMapping("/{subscriptionId}")
    public ApiResponse<GetMySubscriptionDetailInfoResponse> getMySubscriptionDetailInfo(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionId) {
        GetMySubscriptionDetailInfoDto mySubscriptionDetailInfo = subscriptionService.getMySubscriptionDetailInfo(memberId, subscriptionId);
        GetMySubscriptionDetailInfoResponse response = GetMySubscriptionDetailInfoResponse.from(mySubscriptionDetailInfo);

        return ApiResponse.success(GET_MY_SUBSCRIPTION_DETAIL_INFO, response);
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

    @GetMapping("/my/payment-soon")
    public ApiResponse<GetPaymentSoonResponse> getPaymentSoon(@AuthenticationPrincipal Long memberId) {
        List<GetPaymentSoonDto> services = subscriptionService.getPaymentSoon(memberId);
        GetPaymentSoonResponse response = new GetPaymentSoonResponse(services);

        return ApiResponse.success(GET_PAYMENT_SOON, response);
    }

    @GetMapping("/my/total-payments")
    public ApiResponse<GetPaymentTotalResponse> getPaymentTotal(@AuthenticationPrincipal Long memberId) {
        GetPaymentTotalResponse response = subscriptionService.getPaymentTotal(memberId);

        return ApiResponse.success(GET_PAYMENT_TOTAL, response);
    }

    @PatchMapping("/{subscriptionId}/favorite")
    public ApiResponse<Void> updateIsFavorite(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionId) {
        subscriptionService.updateIsFavorite(memberId, subscriptionId);

        return ApiResponse.success(UPDATE_IS_FAVORITE);
    }
}
