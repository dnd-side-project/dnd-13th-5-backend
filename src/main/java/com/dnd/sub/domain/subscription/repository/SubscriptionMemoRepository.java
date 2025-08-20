package com.dnd.sub.domain.subscription.repository;

import com.dnd.sub.domain.subscription.entity.SubscriptionMemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionMemoRepository extends JpaRepository<SubscriptionMemo, Long> {
}
