package com.dnd.sub.domain.product.service;

import com.dnd.sub.domain.product.entity.ProductCategoryType;
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

    public List<String> getProductCategories() {
        return Arrays.stream(ProductCategoryType.values())
            .map(ProductCategoryType::getCategory)
            .toList();
    }
}
