package com.dnd.sub.domain.subscription.controller;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.subscription.dto.request.SaveCustomSubscriptionRequest;
import com.dnd.sub.domain.subscription.dto.request.SaveSubscriptionRequest;
import com.dnd.sub.domain.subscription.dto.request.UpdateSubscriptionDetailRequest;
import com.dnd.sub.domain.subscription.dto.response.GetMySubscriptionDetailInfoResponse;
import com.dnd.sub.domain.subscription.dto.response.GetMySubscriptionsResponse;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentSoonResponse;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentTotalResponse;
import com.dnd.sub.domain.subscription.dto.response.GetUnsubscribeUrlResponse;
import com.dnd.sub.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "사용자 구독 API", description = "사용자 구독과 관련한 API입니다.")
public interface SubscriptionControllerDocs {

    @Operation(
            summary = "구독 등록",
            description = "구독을 등록합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping
    public ApiResponse<Void> saveSubscription(@AuthenticationPrincipal Long memberId, @RequestBody SaveSubscriptionRequest request);

    @Operation(
            summary = "내 구독 서비스 전체 조회",
            description = "내 구독을 전체 조회 합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/my")
    public ApiResponse<GetMySubscriptionsResponse> getMySubscriptions(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(required = false) ProductCategoryType category,
            @RequestParam(required = false) SubscriptionSortType sort
    );

    @Operation(
        summary = "커스텀 구독 등록",
        description = "커스텀 구독을 등록 합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PostMapping("/custom")
    public ApiResponse<Void> saveCustomSubscription(@AuthenticationPrincipal Long memberId, @RequestBody SaveCustomSubscriptionRequest request);

    @Operation(
            summary = "즐겨찾는 정기 결제 서비스 전체 조회",
            description = "즐겨찾기한 구독을 조회합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/my/favorites")
    public ApiResponse<GetMySubscriptionsResponse> getMyFavorites(
            @AuthenticationPrincipal Long memberId,
            @RequestParam(required = false) ProductCategoryType category,
            @RequestParam(required = false) SubscriptionSortType sort
    );

    @Operation(
        summary = "내 구독 상세 정보",
        description = "내구독 상세 정보를 조회합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/{subscriptionId}")
    public ApiResponse<GetMySubscriptionDetailInfoResponse> getMySubscriptionDetailInfo(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionId);

    @Operation(
            summary = "결제 임박한 정기 결제 서비스 조회",
            description = "다음 결제일이 7일 이하인 구독들을 조회합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/my/payment-soon")
    public ApiResponse<GetPaymentSoonResponse> getPaymentSoon(@AuthenticationPrincipal Long memberId);

    @Operation(
            summary = "사용자 월간 결제 요약",
            description = "사용자의 월간 결제를 요약합니다(메인페이지 활용)",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/my/total-payments")
    public ApiResponse<GetPaymentTotalResponse> getPaymentTotal(@AuthenticationPrincipal Long memberId);

    @Operation(
            summary = "즐겨찾기 추가 및 해지 api",
            description = "해당 구독을 즐겨찾기 추가 혹은 해제 합니다.",
            security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/{subscriptionId}/favorite")
    public ApiResponse<Void> updateIsFavorite(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionId);

    @Operation(
        summary = "구독 해지 링크 조회 api",
        description = "구독 해지 링크를 조회합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/{subscriptionsId}/unsubscription")
    public ApiResponse<GetUnsubscribeUrlResponse> getUnsubscribeUrl(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionsId);

    @Operation(
        summary = "구독 상세 수정 api",
        description = "구독 상세 내용을 수정합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/{subscriptionId}")
    public ApiResponse<Void> updateSubscriptionDetail(
        @AuthenticationPrincipal Long memberId,
        @PathVariable Long subscriptionId,
        @RequestBody UpdateSubscriptionDetailRequest request
    );

    @Operation(
        summary = "구독 삭제 api",
        description = "구독을 삭제 합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @DeleteMapping("/{subscriptionId}")
    public ApiResponse<Void> deleteSubscription(@AuthenticationPrincipal Long memberId, @PathVariable Long subscriptionId);
}
