package com.dnd.sub.domain.product.service;

import com.dnd.sub.domain.product.dto.GetAllPlanOfProductDto;
import com.dnd.sub.domain.product.dto.GetProductDto;
import com.dnd.sub.domain.product.dto.GetSelectedProductsInfoDto;
import com.dnd.sub.domain.product.entity.Product;
import com.dnd.sub.domain.product.entity.ProductCategoryType;
import com.dnd.sub.domain.product.entity.ProductPlan;
import com.dnd.sub.domain.product.repository.ProductPlanRepository;
import com.dnd.sub.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductPlanRepository productPlanRepository;

    public List<GetProductDto> getAllProducts(ProductCategoryType category) {
        List<Product> products = productRepository.findAllByCategory(category);

        return products.stream().map(p -> {
            List<ProductPlan> productPlans = productPlanRepository.findAllByProductId(p.getId());

            return new GetProductDto(
                p.getId(),
                p.getName(),
                p.getCategory(),
                p.getImageUrl(),
                findPlanMinPrice(productPlans),
                findPlanMaxPrice(productPlans)
            );
        }).toList();
    }

    private int findPlanMinPrice(List<ProductPlan> plans) {
        return plans.stream()
            .mapToInt(ProductPlan::getPrice)
            .min()
            .orElse(0);
    }

    private int findPlanMaxPrice(List<ProductPlan> plans) {
        return plans.stream()
            .mapToInt(ProductPlan::getPrice)
            .max()
            .orElse(0);
    }

    public List<GetSelectedProductsInfoDto> getSelectedProductsInfo(List<Long> productIds) {
        List<Product> products = productRepository.findAllById(productIds);

        return products.stream().map(p -> {
            List<ProductPlan> productPlans = productPlanRepository.findAllByProductId(p.getId());

            List<GetSelectedProductsInfoDto.ProductPlans> plans = productPlans.stream()
                .map(plan -> new GetSelectedProductsInfoDto.ProductPlans(
                    plan.getId(),
                    plan.getName(),
                    plan.getBenefit()
                ))
                .toList();

            return new GetSelectedProductsInfoDto(
                p.getId(),
                p.getName(),
                p.getImageUrl(),
                plans
            );
        }).toList();
    }

    public List<String> getProductCategories() {
        return Arrays.stream(ProductCategoryType.values())
            .map(ProductCategoryType::getCategory)
            .toList();
    }

    public List<GetAllPlanOfProductDto> getAllPlanOfProduct(Long productId) {
        List<ProductPlan> plans = productPlanRepository.findAllByProductId(productId);

        return plans.stream().map(p -> new GetAllPlanOfProductDto(
            p.getId(),
            p.getName(),
            p.getPrice(),
            p.getBenefit()
        )).toList();
    }
}
