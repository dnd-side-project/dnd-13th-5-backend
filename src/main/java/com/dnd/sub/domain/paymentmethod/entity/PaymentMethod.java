package com.dnd.sub.domain.paymentmethod.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment_method")
@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private PaymentMethodType type;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;

    public PaymentMethod(final PaymentMethodType type, final String name, final String imageUrl) {
        this.type = type;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
