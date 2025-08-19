package com.dnd.sub.domain.member.entity;

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

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "monthly_report")
@Entity
public class MonthlyReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "month", length = 20, nullable = false)
    private MonthType month;

    @Column(name = "monthly_spending")
    private int monthlySpending;

    @Column(name = "subscription_count")
    private int subscriptionCount;

    @Builder
    public MonthlyReport(
        final Member member,
        final MonthType month,
        final int monthlySpending,
        final int subscriptionCount
    ) {
        this.member = member;
        this.month = month;
        this.monthlySpending = monthlySpending;
        this.subscriptionCount = subscriptionCount;
    }
}
