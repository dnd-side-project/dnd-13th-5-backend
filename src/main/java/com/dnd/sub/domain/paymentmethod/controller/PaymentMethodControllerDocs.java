package com.dnd.sub.domain.paymentmethod.controller;

import com.dnd.sub.domain.member.dto.request.UpdateMemberRequest;
import com.dnd.sub.domain.member.dto.response.MemberInfoResponse;
import com.dnd.sub.domain.paymentmethod.dto.GetAllPaymentMethodsDto;
import com.dnd.sub.domain.paymentmethod.dto.response.GetAllPaymentMethodsResponse;
import com.dnd.sub.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "결제수단 API", description = "결제수단과 관련한 API입니다.")
public interface PaymentMethodControllerDocs {

    @Operation(
        summary = "결제 수단조회",
        description = "결제수단을 조회합니다."
    )
    @GetMapping
    public ApiResponse<GetAllPaymentMethodsResponse> getAllPaymentMethods();
}

