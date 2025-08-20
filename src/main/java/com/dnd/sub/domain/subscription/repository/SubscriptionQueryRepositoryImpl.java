package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.entity.QProduct;
import com.dnd.sub.domain.product.entity.QProductPlan;
import com.dnd.sub.domain.subscription.controller.SubscriptionSortType;
import com.dnd.sub.domain.subscription.dto.GetMySubscriptionDto;
import com.dnd.sub.domain.subscription.entity.QSubscription;
import com.dnd.sub.domain.subscription.entity.Subscription;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.numberTemplate;

@RequiredArgsConstructor
public class SubscriptionQueryRepositoryImpl implements SubscriptionQueryRepository {

    private final JPAQueryFactory query;

    @Override
    public List<GetMySubscriptionDto> findMySubscriptions(Long memberId, ProductCategoryType category, SubscriptionSortType sort) {
        QSubscription s = QSubscription.subscription;
        QProduct p = QProduct.product;
        QProductPlan pp = QProductPlan.productPlan;

        BooleanBuilder builder = new BooleanBuilder()
            .and(s.member.id.eq(memberId));

        if (category != null) {
            builder.and(p.category.eq(category));
        }

        List<Tuple> tuples = query
            .select(s, p, pp.price)
            .from(s)
            .join(s.product, p)
            .leftJoin(pp).on(pp.product.eq(p))
            .where(builder)
            .orderBy(buildOrderSpec(sort, s, p, pp))
            .fetch();

        List<GetMySubscriptionDto> services = tuples.stream().map(t -> {
            Subscription sub = t.get(s);
            Product prod = t.get(p);
            int price = t.get(pp.price);

            return new GetMySubscriptionDto(
                sub.getId(),
                prod.getName(),
                prod.getCategory(),
                sub.getPayCycleNum(),
                sub.getPayCycleUnit(),
                price,
                sub.isFavorite(),
                prod.getImageUrl()
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