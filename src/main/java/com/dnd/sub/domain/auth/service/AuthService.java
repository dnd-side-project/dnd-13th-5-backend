package com.dnd.sub.domain.auth.service;

import com.dnd.sub.domain.auth.dto.response.TokenResponse;
import com.dnd.sub.domain.member.service.MemberService;
import com.dnd.sub.global.properties.JwtProperties;
import com.dnd.sub.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public TokenResponse reissueByRefresh(String refreshToken) {

        Long memberId = jwtProvider.extractUserId(refreshToken);

        String newAccess  = jwtProvider.generateToken(memberId);

        if (jwtProvider.isReFreshTokenExpiredSoon(refreshToken)) {
            String newRefresh = jwtProvider.generateRefreshToken(memberId);

            refreshTokenService.removeRefresh(refreshToken);
            refreshTokenService.addRefresh(memberId, newRefresh);

            return new TokenResponse(newAccess, newRefresh);
        }

        return new TokenResponse(newAccess, refreshToken);
    }
    
}
