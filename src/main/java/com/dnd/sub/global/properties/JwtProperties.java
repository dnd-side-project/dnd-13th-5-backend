package com.dnd.sub.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String secret,
    long accessExpiration,
    long refreshExpiration
) {
}
