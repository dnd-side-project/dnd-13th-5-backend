package com.dnd.sub.domain.member.service;

import com.dnd.sub.domain.member.dto.KakaoTokenResponsed;
import com.dnd.sub.global.properties.KakaoProperties;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoProperties kakaoProperties;
    private final RestClient restClient;

    public String getCodeUrl(){
       return kakaoProperties.authUrl() +
            "?scope=profile_nickname,account_email&response_type=code&client_id=" + kakaoProperties.clientId() +
            "&redirect_uri=" + kakaoProperties.redirectUrl();

    }

    public KakaoTokenResponsed getKakaoAccessToken(String code){
        URI uri = URI.create(kakaoProperties.accessTokenUrl());
        LinkedMultiValueMap<String, String> body = createBody(code);

        return restClient.post()
            .uri(uri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(body)
            .retrieve()
            .body(KakaoTokenResponsed.class);


    }

    private LinkedMultiValueMap<String, String> createBody(String code){
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", kakaoProperties.clientId());
        body.add("redirect_uri", kakaoProperties.redirectUrl());
        body.add("code", code);
        return body;
    }

}
