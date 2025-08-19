package com.dnd.sub.domain.product.entity;

import com.dnd.sub.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 100, nullable = false)
    private ProductCategoryType category;

    @Column(name = "image_url", length = 500, nullable = false)
    private String imageUrl;

    @Column(name = "unsubscribe_url", length = 500, nullable = false)
    private String unsubscribeUrl;

    @Column(name = "is_admin_written", columnDefinition = "TINYINT(1)", nullable = false)
    private boolean isAdminWritten = false;

    @Builder
    public Product(
        final String name,
        final ProductCategoryType category,
        final String imageUrl,
        final String unsubscribeUrl
    ) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.unsubscribeUrl = unsubscribeUrl;
    }
}
