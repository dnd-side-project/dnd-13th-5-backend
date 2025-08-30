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
import com.dnd.sub.domain.subscription.entity.PayCycleUnitType;
import com.dnd.sub.domain.subscription.entity.QSubscription;
import com.dnd.sub.domain.subscription.entity.Subscription;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
                    .and(pp.id.eq(s.planId))
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
            int personalPrice = price / sub.getParticipantCount();

            return new GetPaymentSoonDto(
                    sub.getId(),
                    prod.getName(),
                    personalPrice,
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
                .where(s.member.id.eq(memberId))
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

            if (sub.getStartedAt() == null) {
                continue;
            }
            int personalPrice = price / sub.getParticipantCount();
            PayCycleUnitType cycleUnit = sub.getPayCycleUnit();

            if (cycleUnit == PayCycleUnitType.WEEK) {
                LocalDate prevPayDay = sub.getPreviousPaymentDay();

                if (prevPayDay != null) {
                    LocalDate currentPaymentDay = prevPayDay;

                    // 과거 방향으로 결제한 금액
                    while (!currentPaymentDay.isBefore(startDay)) {
                        if (!currentPaymentDay.isAfter(endDay) && !currentPaymentDay.isAfter(today)) {
                            totalAmount += personalPrice;
                            usedAmount += personalPrice;
                        }
                        currentPaymentDay = currentPaymentDay.minusWeeks(1);
                    }

                    // 미래 방향으로 결제할 금액
                    LocalDate nextPayDay = sub.getNextPaymentDay();
                    if (nextPayDay != null) {
                        currentPaymentDay = nextPayDay;
                        while (!currentPaymentDay.isAfter(endDay)) {
                            if (!currentPaymentDay.isBefore(startDay) && currentPaymentDay.isAfter(today)) {
                                totalAmount += personalPrice;
                                remainingAmount += personalPrice;
                            }
                            currentPaymentDay = currentPaymentDay.plusWeeks(1);
                        }
                    }
                }
            }
            else {
                LocalDate prevPayDay = sub.getPreviousPaymentDay();
                LocalDate nextPayDay = sub.getNextPaymentDay();

                // 이전 결제일이 이번달인 경우
                boolean isPrevThisMonth = false;
                if (prevPayDay != null) {
                    isPrevThisMonth = !prevPayDay.isBefore(startDay) &&
                        !prevPayDay.isAfter(endDay);
                }
                // 다음 결제일이 이번달인 경우
                boolean isNextThisMonth = false;
                if (nextPayDay != null) {
                    isNextThisMonth = !nextPayDay.isBefore(startDay) &&
                        !nextPayDay.isAfter(endDay) &&
                        nextPayDay.isAfter(today);
                }

                boolean isPrevPay = isPrevThisMonth && !prevPayDay.isAfter(today);

                if (isPrevThisMonth || isNextThisMonth) {
                    totalAmount += personalPrice;
                }

                if (isPrevPay) {
                    usedAmount += personalPrice;
                }

                if (isNextThisMonth) {
                    remainingAmount += personalPrice;
                }
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

            int personalPrice = price / sub.getParticipantCount();
            return new GetMySubscriptionDto(
                sub.getId(),
                prod.getName(),
                prod.getCategory(),
                sub.getPayCycleUnit(),
                planName,
                personalPrice,
                sub.isFavorite(),
                !prod.isAdminWritten(),
                prod.getImageUrl(),
                sub.getStartedAt()
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
            case DUESOON -> new OrderSpecifier<?>[]{ s.nextPaymentDay.asc()};
        };
    }
}