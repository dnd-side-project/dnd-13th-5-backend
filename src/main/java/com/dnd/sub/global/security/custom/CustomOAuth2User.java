package com.dnd.sub.global.security.custom;

import com.dnd.sub.global.security.dto.KakaoAuthMemberDto;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final KakaoAuthMemberDto authMemberDto;


    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getName() {
        return authMemberDto.nickname();
    }
}
