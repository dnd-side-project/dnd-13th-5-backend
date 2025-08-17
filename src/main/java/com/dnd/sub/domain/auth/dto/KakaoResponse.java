package com.dnd.sub.domain.auth.dto;

import java.util.Map;

public record KakaoResponse(Map<String, Object> attributes) {

    public String getKakaoId(){
         return attributes.get("id").toString();
    }

    public String getNickname(){
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        return profile.get("nickname").toString();
    }

    public String getEmail(){
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        return kakaoAccount.get("email").toString();
    }



}
