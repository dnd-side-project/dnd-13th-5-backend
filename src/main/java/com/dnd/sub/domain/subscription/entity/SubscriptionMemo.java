package com.dnd.sub.domain.subscription.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subscription_memo")
@Entity
public class SubscriptionMemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "subscription_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Subscription subscription;

    @Column(name = "content", length = 200, nullable = false)
    private String content;

    public SubscriptionMemo(final Subscription subscription, final String content) {
        this.subscription = subscription;
        this.content = content;
    }
}
