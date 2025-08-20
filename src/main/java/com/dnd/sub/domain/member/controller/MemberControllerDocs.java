package com.dnd.sub.domain.member.controller;

import com.dnd.sub.domain.member.dto.request.UpdateMemberRequest;
import com.dnd.sub.domain.member.dto.response.MemberInfoResponse;
import com.dnd.sub.domain.member.dto.response.MemberSuccessCode;
import com.dnd.sub.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "멤버 API", description = "멤버와 관련한 API입니다.")
public interface MemberControllerDocs {

    @Operation(
        summary = "내 정보 조회",
        description = "현재 로그인된 회원의 상세 정보를 조회합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @GetMapping("/my/info")
    ApiResponse<MemberInfoResponse> getMemberInfo(@AuthenticationPrincipal Long memberId);

    @Operation(
        summary = "내 정보 수정",
        description = "현재 로그인된 회원의 정보(이메일)을 수정합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/my/info")
    ApiResponse<MemberInfoResponse> updateMemberInfo(@AuthenticationPrincipal Long memberId, @RequestBody UpdateMemberRequest request);

    @Operation(
        summary = "내 알람 상태 수정",
        description = "내 이메일 알람 상태를 수정합니다.",
        security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @PatchMapping("/my/notification")
    ApiResponse<MemberInfoResponse> updateNoti(@AuthenticationPrincipal Long memberId);

}
