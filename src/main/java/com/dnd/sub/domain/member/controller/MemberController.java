package com.dnd.sub.domain.member.controller;

import com.dnd.sub.domain.member.dto.request.UpdateMemberRequest;
import com.dnd.sub.domain.member.dto.response.MemberInfoResponse;
import com.dnd.sub.domain.member.dto.response.MemberSuccessCode;
import com.dnd.sub.domain.member.service.MemberService;
import com.dnd.sub.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    @GetMapping("/my/info")
    public ApiResponse<MemberInfoResponse> getMemberInfo(@AuthenticationPrincipal Long memberId) {
        MemberInfoResponse response = memberService.getMemberInfo(memberId);
        return ApiResponse.success(MemberSuccessCode.MEMBER_INFO_OK, response);
    }

    @PatchMapping("/my/info")
    public ApiResponse<MemberInfoResponse> updateMemberInfo(@AuthenticationPrincipal Long memberId,
        @Valid @RequestBody UpdateMemberRequest request) {
        MemberInfoResponse response = memberService.updateMemberInfo(memberId, request);
        return ApiResponse.success(MemberSuccessCode.MEMBER_INFO_UPDATE, response);
    }

    @PatchMapping("/my/notification")
    public ApiResponse<MemberInfoResponse> updateNotificationStatus(
        @AuthenticationPrincipal Long memberId) {
        MemberInfoResponse response = memberService.updateNotificationStatus(memberId);
        return ApiResponse.success(MemberSuccessCode.MEMBER_NOTI_UPDATE, response);
    }

    @DeleteMapping("/withdraw")
    public ApiResponse<Void> withdraw(@AuthenticationPrincipal Long memberId) {
        memberService.deleteMember(memberId);

        return ApiResponse.success(MemberSuccessCode.MEMBER_WITHDRAW_OK);
    }
    
}
