package com.dnd.sub.domain.product.repository;

import com.dnd.sub.domain.product.entity.ProductPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPlanRepository extends JpaRepository<ProductPlan, Long> {
    List<ProductPlan> findByProductId(Long productId);
}
