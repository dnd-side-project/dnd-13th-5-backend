package com.dnd.sub.domain.product.repository;

import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(ProductCategoryType category);

    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY function('RAND')")
    List<Product> findRandomProductsByCategory(@Param("category") ProductCategoryType category, Pageable pageable);
}
