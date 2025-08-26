package com.dnd.sub.domain.paymentmethod.controller;

import com.dnd.sub.domain.paymentmethod.dto.GetAllPaymentMethodsDto;
import com.dnd.sub.domain.paymentmethod.dto.response.GetAllPaymentMethodsResponse;
import com.dnd.sub.domain.paymentmethod.service.PaymentMethodService;
import com.dnd.sub.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dnd.sub.domain.paymentmethod.dto.response.PaymentMethodSuccessCode.GET_ALL_PAYMENT_METHODS;

@RequiredArgsConstructor
@RequestMapping("/api/payment-methods")
@RestController
public class PaymentMethodController implements PaymentMethodControllerDocs{

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ApiResponse<GetAllPaymentMethodsResponse> getAllPaymentMethods() {
        GetAllPaymentMethodsDto paymentMethods = paymentMethodService.getAllPaymentMethods();
        GetAllPaymentMethodsResponse response = GetAllPaymentMethodsResponse.from(paymentMethods);

        return ApiResponse.success(GET_ALL_PAYMENT_METHODS, response);
    }
}
