package com.dnd.sub.domain.auth.controller;

import com.dnd.sub.domain.auth.dto.response.TokenResponse;
import com.dnd.sub.domain.auth.service.AuthService;
import com.dnd.sub.global.dto.ApiResponse;
import com.dnd.sub.global.enums.GlobalSuccessCode;
import com.dnd.sub.global.enums.TokenErrorCode;
import com.dnd.sub.global.exception.TokenException;
import com.dnd.sub.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/reissue")
    public ApiResponse<Void> reissue(@CookieValue(value = "refresh_token", required = false) String refreshToken,
        HttpServletResponse response) {
        if (refreshToken == null) {
            throw new TokenException(TokenErrorCode.REFRESH_NOT_FOUND);
        }
        TokenResponse tokenResponse = authService.reissueByRefresh(refreshToken);

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokenResponse.accessToken());
        ResponseCookie responseCookie = CookieUtil.createCookie("refresh_token",
            tokenResponse.refreshToken(), 60 * 60 * 24 * 14);
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return ApiResponse.success(GlobalSuccessCode.OK);
    }

}
