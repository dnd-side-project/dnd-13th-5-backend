package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionQueryRepository {

    boolean existsByMemberIdAndId(Long memberId, Long subscriptionId);
}
