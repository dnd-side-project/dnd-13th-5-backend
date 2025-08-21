package com.dnd.sub.domain.subscription.entity;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subscription")
@Entity
public class Subscription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "payment_method_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentMethod paymentMethod;

    @Column(name = "started_at", nullable = false)
    private LocalDate startedAt;

    @Column(name = "participant_count")
    private int participantCount;

    @Column(name = "pay_type", length = 20, nullable = false)
    private String payType;

    @Column(name = "pay_cycle_num", nullable = true)
    private int payCycleNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_cycle_unit", length = 20, nullable = true)
    private PayCycleUnitType payCycleUnit;

    @Column(name = "is_favorite", columnDefinition = "TINYINT(1)", nullable = false)
    private boolean isFavorite = false;

    public void updateIsFavorite() {
        this.isFavorite = !this.isFavorite;
    }

    @Builder
    public Subscription(
        final Member member,
        final Product product,
        final PaymentMethod paymentMethod,
        final LocalDate startedAt,
        final int participantCount,
        final String payType,
        final int payCycleNum,
        final PayCycleUnitType payCycleUnit
    ) {
        this.member = member;
        this.product = product;
        this.paymentMethod = paymentMethod;
        this.startedAt = startedAt;
        this.participantCount = participantCount;
        this.payType = payType;
        this.payCycleNum = payCycleNum;
        this.payCycleUnit = payCycleUnit;
    }
}
