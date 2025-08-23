package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.member.entity.QMember;
import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.entity.QProduct;
import com.dnd.sub.domain.product.entity.QProductPlan;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.dto.GetPaymentSoonDto;
import com.dnd.sub.domain.subscription.dto.response.GetPaymentTotalResponse;
import com.dnd.sub.domain.subscription.entity.QSubscription;
import com.dnd.sub.domain.subscription.entity.Subscription;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.numberTemplate;

@RequiredArgsConstructor
public class SubscriptionQueryRepositoryImpl implements SubscriptionQueryRepository {

    private final JPAQueryFactory query;

    private static final QSubscription s = QSubscription.subscription;
    private static final QProduct p = QProduct.product;
    private static final QProductPlan pp = QProductPlan.productPlan;
    private static final QMember m = QMember.member;

    @Override
    public List<GetMySubscriptionDto> findMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {

        BooleanBuilder builder = new BooleanBuilder()
            .and(s.member.id.eq(memberId));

        if (category != null) {
            builder.and(p.category.eq(category));
        }

        return findSubscriptions(builder, sort);
    }

    @Override
    public List<GetMySubscriptionDto> findMyFavorites(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {

        BooleanBuilder builder = new BooleanBuilder()
            .and(s.member.id.eq(memberId))
            .and(s.isFavorite.eq(true));

        if (category != null) {
            builder.and(p.category.eq(category));
        }

        return findSubscriptions(builder, sort);
    }

    @Override
    public List<GetPaymentSoonDto> findPaymentSoon(Long memberId) {

        List<Tuple> tuples = query
                .select(s, p, pp.price)
                .from(s)
                .join(s.product, p)
                .leftJoin(pp).on(pp.id.eq(s.planId))
                .where(s.member.id.eq(memberId)
                        .and(s.nextPaymentDay.isNotNull())
                        .and(s.nextPaymentDay.goe(LocalDate.now()))
                        .and(s.nextPaymentDay.loe(LocalDate.now().plusDays(7))))
                .orderBy(s.nextPaymentDay.asc())
                .limit(5)
                .fetch();

        return tuples.stream().map(t -> {
            Subscription sub = t.get(s);
            Product prod = t.get(p);
            int price = t.get(pp.price);

            return new GetPaymentSoonDto(
                    sub.getId(),
                    prod.getName(),
                    price,
                    sub.getNextPaymentDay()
            );
        }).toList();
    }

    @Override
    public GetPaymentTotalResponse findPaymentTotal(Long memberId) {
        LocalDate today = LocalDate.now();
        LocalDate startDay = today.withDayOfMonth(1);
        LocalDate endDay = today.withDayOfMonth(today.lengthOfMonth());

        List<Tuple> tuples = query
                .select(m.name, s, pp.price)
                .from(s)
                .join(s.member, m)
                .join(s.product, p)
                .leftJoin(pp).on(pp.id.eq(s.planId))
                .where(s.member.id.eq(memberId)
                        .and(s.startedAt.isNotNull()))
                .fetch();

        if (tuples.isEmpty()) {
            String userName = query
                    .select(m.name)
                    .from(m)
                    .where(m.id.eq(memberId))
                    .fetchOne();
            return new GetPaymentTotalResponse(userName, 0, 0, 0, 0);
        }

        int totalAmount = 0;
        int usedAmount = 0;
        int remainingAmount = 0;
        int subCount = tuples.size();

        for (Tuple tuple : tuples) {
            Subscription sub = tuple.get(s);
            Integer price = tuple.get(pp.price);

            LocalDate prevPayDay = sub.getPreviousPaymentDay();
            LocalDate nextPayDay = sub.getNextPaymentDay();

            // 이전 결제일이 이번달인 경우
            boolean isPrevThisMonth = prevPayDay.isAfter(startDay.minusDays(1)) &&
                    prevPayDay.isBefore(endDay.plusDays(1)) &&
                    prevPayDay.isBefore(today.plusDays(1));

            // 다음 결제일이 이번달인 경우
            boolean isNextThisMonth = nextPayDay.isAfter(startDay.minusDays(1)) &&
                    nextPayDay.isBefore(endDay.plusDays(1)) &&
                    nextPayDay.isAfter(today);

            if (isPrevThisMonth || isNextThisMonth) {
                totalAmount += price;
            }

            if (isPrevThisMonth) {
                usedAmount += price;
            }

            if (isNextThisMonth) {
                remainingAmount += price;
            }
        }

        int progressPercentage = 0;
        if (totalAmount > 0) {
            progressPercentage = (100 * usedAmount) / totalAmount;
        }

        return new GetPaymentTotalResponse(
                tuples.get(0).get(m.name),
                totalAmount,
                remainingAmount,
                progressPercentage,
                subCount
        );
    }

    private List<GetMySubscriptionDto> findSubscriptions(BooleanBuilder builder,
        SubscriptionSortType sort) {

        List<Tuple> tuples = query
            .select(s, p, pp.name, pp.price)
            .from(s)
            .join(s.product, p)
            .leftJoin(pp).on(pp.id.eq(s.planId))
            .where(builder)
            .orderBy(buildOrderSpec(sort, s, p, pp))
            .fetch();

        List<GetMySubscriptionDto> services = tuples.stream().map(t -> {
            Subscription sub = t.get(s);
            Product prod = t.get(p);
            String planName = t.get(pp.name);
            int price = t.get(pp.price);

            return new GetMySubscriptionDto(
                sub.getId(),
                prod.getName(),
                prod.getCategory(),
                sub.getPayCycleNum(),
                sub.getPayCycleUnit(),
                planName,
                price,
                sub.isFavorite(),
                    prod.getImageUrl(),
                    sub.getNextPaymentDay()
            );
        }).toList();

        return services;
    }
    
    private OrderSpecifier<?>[] buildOrderSpec(SubscriptionSortType sort, QSubscription s, QProduct p, QProductPlan pp) {
        if (sort == null) {
            return new OrderSpecifier<?>[]{ p.name.asc() };
        }

        return switch (sort) {
            case NAME -> new OrderSpecifier<?>[]{ p.name.asc() };
            case CHEAPEST -> new OrderSpecifier<?>[]{ pp.price.asc() };
            case OLDESTFIRST -> new OrderSpecifier<?>[]{ s.startedAt.asc() };
            case DUESOON -> new OrderSpecifier<?>[]{
                numberTemplate(Long.class,
                    "UNIX_TIMESTAMP(DATE_ADD({0}, INTERVAL {1} {2}))",
                    s.startedAt, s.payCycleNum, s.payCycleUnit.stringValue()
                ).asc()
            };
        };
    }
}