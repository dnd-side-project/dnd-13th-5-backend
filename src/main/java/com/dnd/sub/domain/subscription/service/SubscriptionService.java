package com.dnd.sub.domain.subscription.service;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.exception.MemberErrorCode;
import com.dnd.sub.domain.member.exception.MemberException;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import com.dnd.sub.domain.paymentmethod.exception.PaymentMethodException;
import com.dnd.sub.domain.paymentmethod.repository.PaymentMethodRepository;
import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.entity.ProductPlan;
import com.dnd.sub.domain.product.exception.ProductErrorCode;
import com.dnd.sub.domain.product.exception.ProductException;
import com.dnd.sub.domain.product.repository.ProductPlanRepository;
import com.dnd.sub.domain.product.repository.ProductRepository;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDetailInfoDto;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;
import com.dnd.sub.domain.subscription.dto.SaveCustomSubscriptionDto;
import com.dnd.sub.domain.subscription.dto.SaveSubscriptionDto;
import com.dnd.sub.domain.subscription.dto.UpdateSubscriptionDetailDto;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentTotalResponse;
import com.dnd.sub.domain.subscription.dto.response.GetUnsubscribeUrlResponse;
import com.dnd.sub.domain.subscription.entity.Subscription;
import com.dnd.sub.domain.subscription.exception.SubscriptionException;
import com.dnd.sub.domain.subscription.repository.SubscriptionRepository;
import com.dnd.sub.global.util.PaymentCycleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.dnd.sub.domain.paymentmethod.exception.PaymentMethodErrorCode.PAYMENT_METHOD_NOT_FOUND;
import static com.dnd.sub.domain.subscription.exception.SubscriptionErrorCode.MEMBER_SUBSCRIPTION_NOT_FOUND;
import static com.dnd.sub.domain.subscription.exception.SubscriptionErrorCode.SUBSCRIPTION_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final ProductPlanRepository productPlanRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    @Transactional
    public void updatePaymentDay() {
        final LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        List<Subscription> subscriptions = subscriptionRepository.findByNextPaymentDayBefore(today);

        for (Subscription sub : subscriptions) {
            LocalDate oldNextPaymentDay = sub.getNextPaymentDay();
            sub.updatePreviousPaymentDay(oldNextPaymentDay);

            LocalDate newNextPaymentDay = PaymentCycleUtil.nextPaymentDay(oldNextPaymentDay, sub.getStartedAt(), sub.getPayCycleUnit());
            sub.updateNextPaymentDay(newNextPaymentDay);
        }
    }

    @Transactional
    public void saveSubscription(final Long memberId, final SaveSubscriptionDto dto) {
        final Member member = getMember(memberId);
        final Product product = getProduct(dto.productId());
        final PaymentMethod paymentMethod = getPaymentMethod(dto.paymentMethodId());

        final LocalDate previousPaymentDay = PaymentCycleUtil.previousPaymentDay(dto.startedAt(), dto.payCycleUnit());

        final LocalDate nextPaymentDay = PaymentCycleUtil.nextPaymentDay(
            dto.startedAt(),
            dto.startedAt(),
            dto.payCycleUnit()
        );

        Subscription subscription = Subscription.builder()
            .member(member)
            .product(product)
            .paymentMethod(paymentMethod)
            .planId(dto.planId())
            .startedAt(dto.startedAt())
            .previousPaymentDay(previousPaymentDay)
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
            .orElseThrow(() -> new PaymentMethodException(PAYMENT_METHOD_NOT_FOUND));
    }

    @Transactional
    public void saveCustomSubscription(final Long memberId, final SaveCustomSubscriptionDto dto) {
        final Member member = getMember(memberId);
        final PaymentMethod paymentMethod = getPaymentMethod(dto.paymentMethodId());

        final LocalDate previousPaymentDay = PaymentCycleUtil.previousPaymentDay(dto.startedAt(), dto.payCycleUnit());

        final LocalDate nextPaymentDay = PaymentCycleUtil.nextPaymentDay(
            dto.startedAt(),
            dto.startedAt(),
            dto.payCycleUnit()
        );

        Product product = Product.builder()
            .name(dto.productName())
            .category(dto.category())
            .imageUrl(null)
            .unsubscribeUrl(null)
            .build();

        productRepository.save(product);

        ProductPlan productPlan = ProductPlan.builder()
            .product(product)
            .name(null)
            .price(dto.price())
            .benefit(null)
            .build();

        productPlanRepository.save(productPlan);

        Subscription subscription = Subscription.builder()
            .member(member)
            .product(product)
            .paymentMethod(paymentMethod)
            .planId(productPlan.getId())
            .startedAt(dto.startedAt())
            .previousPaymentDay(previousPaymentDay)
            .nextPaymentDay(nextPaymentDay)
            .participantCount(dto.participantCount())
            .payCycleUnit(dto.payCycleUnit())
            .memo(dto.memo())
            .build();

        subscriptionRepository.save(subscription);
    }

    public List<GetMySubscriptionDto> getMySubscriptions(final Long memberId, final ProductCategoryType category, final SubscriptionSortType sort) {
        validateMember(memberId);

        return subscriptionRepository.findMySubscriptions(memberId, category, sort);
    }

    private void validateMember(final Long memberId) {
        if(!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.NOT_FOUND);
        }
    }

    public List<GetMySubscriptionDto> getMyFavorites(final Long memberId, final ProductCategoryType category, final SubscriptionSortType sort) {
        validateMember(memberId);

        return subscriptionRepository.findMyFavorites(memberId, category, sort);
    }

    public List<GetPaymentSoonDto> getPaymentSoon(final Long memberId) {
        validateMember(memberId);

        return subscriptionRepository.findPaymentSoon(memberId);
    }

    public GetPaymentTotalResponse getPaymentTotal(final Long memberId) {
        validateMember(memberId);

        return subscriptionRepository.findPaymentTotal(memberId);
    }

    @Transactional
    public void updateIsFavorite(final Long memberId, final Long subscriptionId) {
        validateMemberSubscription(memberId, subscriptionId);

        final Subscription subscription = getSubscription(subscriptionId);
        subscription.updateIsFavorite();
    }

    public GetUnsubscribeUrlResponse getUnsubscribeUrl(final Long memberId, final Long subscriptionId) {
        validateMemberSubscription(memberId, subscriptionId);

        String unsubscribeUrl = getProductBySubscriptionId(subscriptionId).getUnsubscribeUrl();
        return new GetUnsubscribeUrlResponse(unsubscribeUrl);
    }

    @Transactional
    public void deleteSubscription(final Long memberId, final Long subscriptionId) {
        Subscription subscription = validateMemberSubscription(memberId, subscriptionId);
        Product product = subscription.getProduct();

        subscriptionRepository.delete(subscription);

        if(!product.isAdminWritten()){
            productPlanRepository.deleteByProductId(product.getId());
            productRepository.delete(product);
        }

    }

    @Transactional
    public void deleteAllSubscriptions(final Long memberId) {
        List<Subscription> subscriptions = subscriptionRepository.findByMember_Id(memberId);

        if(subscriptions.isEmpty()){
            return;
        }

        for (Subscription subscription : subscriptions) {
            Product product = subscription.getProduct();

            subscriptionRepository.delete(subscription);

            if(!product.isAdminWritten()){
                productPlanRepository.deleteByProductId(product.getId());
                productRepository.delete(product);
            }
        }
    }


    private Subscription validateMemberSubscription(final Long memberId, final Long subscriptionId) {
        return subscriptionRepository.findByIdAndMember_Id(subscriptionId, memberId)
            .orElseThrow(() -> new SubscriptionException(MEMBER_SUBSCRIPTION_NOT_FOUND));
    }

    private Subscription getSubscription(final Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
            .orElseThrow(() -> new SubscriptionException(SUBSCRIPTION_NOT_FOUND));
    }

    public GetMySubscriptionDetailInfoDto getMySubscriptionDetailInfo(final Long memberId, final Long subscriptionId) {
        validateMemberSubscription(memberId, subscriptionId);

        final Subscription subscription = getSubscription(subscriptionId);
        final Product product = getProduct(subscription.getProduct().getId());
        final ProductPlan productPlan = productPlanRepository.findByProduct(product);

        return new GetMySubscriptionDetailInfoDto(
            subscriptionId,
            product.getName(),
            product.getCategory(),
            product.getImageUrl(),
            subscription.getPayCycleUnit(),
            subscription.getStartedAt(),
            (int) subscription.getPayCycleUnit().getChronoUnit().between(subscription.getStartedAt(), LocalDate.now()),
            productPlan.getPrice(),
            productPlan.getName(),
            subscription.getPaymentMethod().getId(),
            subscription.getMemo(),
            subscription.getParticipantCount(),
            productPlan.getBenefit(),
            subscription.isFavorite()
        );
    }

    private Product getProductBySubscriptionId(final Long subscriptionId) {
        return productRepository.findBySubscriptionId(subscriptionId)
            .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }

    @Transactional
    public void updateSubscriptionDetail(final Long memberId, final Long subscriptionId, final UpdateSubscriptionDetailDto dto) {
        validateMemberSubscription(memberId, subscriptionId);

        final Subscription subscription = getSubscription(subscriptionId);
        final Product product = getProduct(subscription.getProduct().getId());
        final ProductPlan productPlan = productPlanRepository.findByProduct(product);

        subscription.updateParticipantCount(dto.participantCount());
        subscription.updatePayCycleUnit(dto.payCycleUnit());
        subscription.updatePaymentMethod(getPaymentMethod(dto.paymentMethodId()));

        final LocalDate newPreviousPaymentDay = PaymentCycleUtil.previousPaymentDay(dto.startedAt(), dto.payCycleUnit());
        final LocalDate newNextPaymentDay = PaymentCycleUtil.nextPaymentDay(dto.startedAt(), dto.startedAt(), dto.payCycleUnit());

        subscription.updateStartedAt(dto.startedAt());
        subscription.updatePreviousPaymentDay(newPreviousPaymentDay);
        subscription.updateNextPaymentDay(newNextPaymentDay);

        if(product.isAdminWritten()) {
            if (dto.planId().isPresent()) {
                subscription.updatePlanId(dto.planId().get());
            }
            return;
        }

        if (dto.productName().isPresent()) {
            product.updateName(dto.productName().get());
        }
        if (dto.price().isPresent()) {
            productPlan.updatePrice(dto.price().get());
        }
    }
}
