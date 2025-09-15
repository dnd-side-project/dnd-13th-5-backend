package com.dnd.sub.domain.paymentmethod.repository;

import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethodType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    
    List<PaymentMethod> findAllByType(PaymentMethodType type);
}
