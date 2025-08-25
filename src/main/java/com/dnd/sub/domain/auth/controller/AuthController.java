package com.dnd.sub.domain.auth.controller;

import com.dnd.sub.domain.auth.dto.response.AuthSuccessCode;
import com.dnd.sub.domain.auth.dto.response.TokenResponse;
import com.dnd.sub.domain.auth.service.AuthService;
import com.dnd.sub.global.dto.ApiResponse;
import com.dnd.sub.global.enums.GlobalSuccessCode;
import com.dnd.sub.domain.auth.exception.TokenErrorCode;
import com.dnd.sub.domain.auth.exception.TokenException;
import com.dnd.sub.global.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    @Operation(
            summary = "토큰 재발급",
            description = "쿠키의 리프레시토큰으로 헤더의 엑세스토큰을 재발급 합니다."
    )
    @GetMapping("/reissue")
    public ApiResponse<Void> reissue(
        @CookieValue(value = "refresh_token", required = false) String refreshToken,
        HttpServletResponse response
    ) {
        if (refreshToken == null) {
            throw new TokenException(TokenErrorCode.REFRESH_NOT_FOUND);
        }
        TokenResponse tokenResponse = authService.reissueByRefresh(refreshToken);

        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + tokenResponse.accessToken());
        ResponseCookie responseCookie = CookieUtil.createCookie("refresh_token",
            tokenResponse.refreshToken(), 60 * 60 * 24 * 14);
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return ApiResponse.success(AuthSuccessCode.REISSUE_OK);
    }

}
