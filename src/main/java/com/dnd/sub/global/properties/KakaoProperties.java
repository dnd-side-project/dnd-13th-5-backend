package com.dnd.sub.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kakao")
public record KakaoProperties(
    String clientId,
    String redirectUrl,
    String authUrl,
    String accessTokenUrl
) {
}
