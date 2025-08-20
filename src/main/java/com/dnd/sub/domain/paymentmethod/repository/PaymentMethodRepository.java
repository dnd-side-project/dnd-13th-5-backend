package com.dnd.sub.domain.paymentmethod.repository;

import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
