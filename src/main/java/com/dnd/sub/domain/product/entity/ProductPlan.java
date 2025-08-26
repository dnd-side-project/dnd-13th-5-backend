package com.dnd.sub.domain.product.entity;

import com.dnd.sub.global.entity.BaseEntity;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product_plan")
@Entity
public class ProductPlan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "name", length = 100, nullable = true)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "benefit", columnDefinition = "TEXT", length = 500, nullable = true)
    private String benefit;

    public void updatePrice(int price) {
        this.price = price;
    }

    @Builder
    public ProductPlan(
        final Product product,
        final String name,
        final int price,
        final String benefit
    ) {
        this.product = product;
        this.name = name;
        this.price = price;
        this.benefit = benefit;
    }
}
