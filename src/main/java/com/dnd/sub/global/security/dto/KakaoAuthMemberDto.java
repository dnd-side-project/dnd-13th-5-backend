package com.dnd.sub.global.security.dto;

import lombok.Builder;

@Builder
public record KakaoAuthMemberDto(
    String kakaoId,
    String nickname,
    String email
) {

}
