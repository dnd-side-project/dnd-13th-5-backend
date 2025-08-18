package com.dnd.sub.global.security.custom;

import com.dnd.sub.global.security.dto.KakaoAuthMemberDto;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final KakaoAuthMemberDto authMemberDto;


    @Override
    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("memberId", authMemberDto.memberId());
        attributes.put("kakaoId", authMemberDto.kakaoId());
        attributes.put("email", authMemberDto.email());
        attributes.put("nickname", authMemberDto.nickname());
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getName() {
        return authMemberDto.nickname();
    }

    public Long getMemberId() {
        return authMemberDto.memberId();
    }
}
