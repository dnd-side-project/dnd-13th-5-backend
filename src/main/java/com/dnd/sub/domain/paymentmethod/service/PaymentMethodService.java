package com.dnd.sub.domain.paymentmethod.service;

import com.dnd.sub.domain.paymentmethod.dto.GetAllPaymentMethodsDto;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethod;
import com.dnd.sub.domain.paymentmethod.entity.PaymentMethodType;
import com.dnd.sub.domain.paymentmethod.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public GetAllPaymentMethodsDto getAllPaymentMethods() {
        final List<PaymentMethod> card = paymentMethodRepository.findAllByType(PaymentMethodType.CARD);
        final List<PaymentMethod> account = paymentMethodRepository.findAllByType(PaymentMethodType.ACCOUNT);
        final List<PaymentMethod> easyPay = paymentMethodRepository.findAllByType(PaymentMethodType.EASY_PAY);

        return new GetAllPaymentMethodsDto(card, account, easyPay);
    }
}
