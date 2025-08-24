package com.dnd.sub.global.security.handler;

import com.dnd.sub.domain.auth.service.RefreshTokenService;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.member.service.MemberService;
import com.dnd.sub.global.security.jwt.JwtProvider;
import com.dnd.sub.global.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CustomOAuth2LogoutHandler implements LogoutHandler {

    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final JwtProvider jwtProvider;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String refreshToken = extractRefreshToken(request.getCookies());
        Long memberId = jwtProvider.extractUserId(refreshToken);
        refreshTokenService.removeRefreshByMemberId(memberId);

        ResponseCookie cookie = CookieUtil.createCookie("refresh_token", "", 0);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        response.setHeader(HttpHeaders.AUTHORIZATION,"");
    }

    public String extractRefreshToken(Cookie[] cookies) {

        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> "refresh_token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
