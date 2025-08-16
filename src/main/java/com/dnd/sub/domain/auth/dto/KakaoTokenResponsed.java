package com.dnd.sub.domain.auth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record KakaoTokenResponsed(
    String accessToken,
    String TokenType,
    String refreshToken,
    String scope,
    int expiresIn,
    int refreshTokenExpiresIn
) {

}
