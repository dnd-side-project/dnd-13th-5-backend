package com.dnd.sub.domain.auth.controller;

import com.dnd.sub.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "인증 API", description = "인증과 관련한 API입니다.")
public interface AuthControllerDocs {

    @GetMapping("/reissue")
    public ApiResponse<Void> reissue(
            @CookieValue(value = "refresh_token", required = false) String refreshToken,
            HttpServletResponse response
    );
}
