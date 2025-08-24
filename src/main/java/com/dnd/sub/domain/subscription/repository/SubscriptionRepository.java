package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>, SubscriptionQueryRepository {

    boolean existsByIdAndMember_Id(Long subscriptionId, Long memberId);

    List<Subscription> findByNextPaymentDayBefore(LocalDate today);
}
