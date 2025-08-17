package com.dnd.sub.domain.auth.dto;

import lombok.Builder;

@Builder
public record KakaoAuthMemberDto(
    String kakaoId,
    String nickname,
    String email
) {

}
