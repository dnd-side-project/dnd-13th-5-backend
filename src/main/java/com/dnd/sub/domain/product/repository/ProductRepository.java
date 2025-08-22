package com.dnd.sub.domain.product.repository;

import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(ProductCategoryType category);
}
