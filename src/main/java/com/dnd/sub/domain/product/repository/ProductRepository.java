package com.dnd.sub.domain.product.repository;

import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(ProductCategoryType category);

    @Query("SELECT s.product FROM Subscription s WHERE s.id = :subscriptionId")
    Optional<Product> findBySubscriptionId(@Param("subscriptionId") Long subscriptionId);
}
