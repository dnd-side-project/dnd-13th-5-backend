package com.dnd.sub.domain.subscription.service;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.exception.MemberErrorCode;
import com.dnd.sub.domain.member.exception.MemberException;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import com.dnd.sub.domain.paymentmethod.exception.PaymentMethodErrorCode;
import com.dnd.sub.domain.paymentmethod.exception.PaymentMethodException;
import com.dnd.sub.domain.paymentmethod.repository.PaymentMethodRepository;
import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.exception.ProductErrorCode;
import com.dnd.sub.domain.product.exception.ProductException;
import com.dnd.sub.domain.product.repository.ProductRepository;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;
import com.dnd.sub.domain.subscription.dto.SaveSubscriptionDto;
import com.dnd.sub.domain.subscription.entity.Subscription;
import com.dnd.sub.domain.subscription.repository.SubscriptionRepository;
import com.dnd.sub.global.util.PaymentCycleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    @Transactional
    public void saveSubscription(final Long memberId, final SaveSubscriptionDto dto) {
        final Member member = getMember(memberId);
        final Product product = getProduct(dto.productId());
        final PaymentMethod paymentMethod = getPaymentMethod(dto.paymentMethodId());

        final LocalDate nextPaymentDay = PaymentCycleUtil.nextPaymentDay(
            dto.startDay(),
            dto.startDay(),
            dto.payCycleUnit()
        );

        Subscription subscription = Subscription.builder()
            .member(member)
            .product(product)
            .paymentMethod(paymentMethod)
            .planId(dto.planId())
            .startedAt(dto.startDay())
            .nextPaymentDay(nextPaymentDay)
            .participantCount(dto.participantCount())
            .payCycleUnit(dto.payCycleUnit())
            .memo(dto.memo())
            .build();

        subscriptionRepository.save(subscription);
    }

    private Member getMember(final Long memberId) {
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
    }

    private Product getProduct(final Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }

    private PaymentMethod getPaymentMethod(final Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
            .orElseThrow(() -> new PaymentMethodException(PaymentMethodErrorCode.PAYMENT_METHOD_NOT_FOUND));
    }

    public List<GetMySubscriptionDto> getMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {
        return subscriptionRepository.findMySubscriptions(memberId, category, sort);
    }

    public List<GetMySubscriptionDto> getMyFavorites(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {
        return subscriptionRepository.findMyFavorites(memberId, category, sort);
    }

    public List<GetPaymentSoonDto> getPaymentSoon(Long memberId) {
        return subscriptionRepository.findPaymentSoon(memberId);
    }

    public GetPaymentTotalResponse getPaymentTotal(Long memberId) {
        return subscriptionRepository.findPaymentTotal(memberId);
    }
}
