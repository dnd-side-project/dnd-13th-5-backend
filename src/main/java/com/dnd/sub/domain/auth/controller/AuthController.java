package com.dnd.sub.domain.auth.controller;

import com.dnd.sub.domain.auth.dto.response.TokenResponse;
import com.dnd.sub.domain.auth.service.AuthService;
import com.dnd.sub.global.dto.ApiResponse;
import com.dnd.sub.global.enums.GlobalSuccessCode;
import com.dnd.sub.global.enums.TokenErrorCode;
import com.dnd.sub.global.exception.TokenException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

        return ApiResponse.success(GlobalSuccessCode.OK);
    }

}
