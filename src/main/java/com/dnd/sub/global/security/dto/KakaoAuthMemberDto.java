package com.dnd.sub.global.security.dto;

import lombok.Builder;

@Builder
public record KakaoAuthMemberDto(
    Long memberId,
    String kakaoId,
    String nickname,
    String email
) {
}
