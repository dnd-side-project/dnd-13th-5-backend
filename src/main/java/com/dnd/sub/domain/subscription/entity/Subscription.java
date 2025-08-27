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

    @Column(name = "plan_id", nullable = true)
    private Long planId;

    @Column(name = "started_at", nullable = true)
    private LocalDate startedAt;

    @Column(name = "previous_payment_day", nullable = true)
    private LocalDate previousPaymentDay;

    @Column(name = "next_payment_day", nullable = true)
    private LocalDate nextPaymentDay;

    @Column(name = "participant_count", nullable = false)
    private int participantCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_cycle_unit", length = 20, nullable = false)
    private PayCycleUnitType payCycleUnit;

    @Column(name = "is_favorite", columnDefinition = "TINYINT(1)", nullable = false)
    private boolean isFavorite = false;

    @Column(name = "memo", length = 200, nullable = true)
    private String memo;

    public void updateIsFavorite() {
        this.isFavorite = !this.isFavorite;
    }

    public void updatePreviousPaymentDay(LocalDate date) {
        this.previousPaymentDay = date;
    }

    public void updateNextPaymentDay(LocalDate date) {
        this.nextPaymentDay = date;
    }

    public void updatePlanId(Long planId) {
        this.planId = planId;
    }

    public void updateParticipantCount(int participantCount) {
        this.participantCount = participantCount;
    }
    public void updatePayCycleUnit(PayCycleUnitType payCycleUnit) {
        this.payCycleUnit = payCycleUnit;
    }

    public void updateStartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public void updatePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private void updateMemo(String memo) { this.memo = memo; }

    @Builder
    public Subscription(
        final Member member,
        final Product product,
        final PaymentMethod paymentMethod,
        final Long planId,
        final LocalDate startedAt,
        final LocalDate previousPaymentDay,
        final LocalDate nextPaymentDay,
        final int participantCount,
        final PayCycleUnitType payCycleUnit,
        final String memo
    ) {
        this.member = member;
        this.product = product;
        this.paymentMethod = paymentMethod;
        this.planId = planId;
        this.startedAt = startedAt;
        this.previousPaymentDay = previousPaymentDay;
        this.nextPaymentDay = nextPaymentDay;
        this.participantCount = participantCount;
        this.payCycleUnit = payCycleUnit;
        this.memo = memo;
    }
}
