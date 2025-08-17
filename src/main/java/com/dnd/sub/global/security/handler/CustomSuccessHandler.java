package com.dnd.sub.global.security.handler;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.dnd.sub.domain.member.service.RefreshTokenService;
import com.dnd.sub.global.security.custom.CustomOAuth2User;
import com.dnd.sub.global.security.jwt.JwtProvider;
import com.dnd.sub.global.util.CookieUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;


    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String accessToken = jwtProvider.generateToken(customOAuth2User.getMemberId());
        String refreshToken = jwtProvider.generateRefreshToken(customOAuth2User.getMemberId());
        ResponseCookie refreshCookie = CookieUtil.createCookie("refresh_cookie", refreshToken, 60*60*24*7);

        refreshTokenService.addRefresh(customOAuth2User.getMemberId(), refreshToken);

        response.addHeader(AUTHORIZATION, "Bearer "+ accessToken);
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        response.sendRedirect("http://localhost:8080/refresh");
    }

}
