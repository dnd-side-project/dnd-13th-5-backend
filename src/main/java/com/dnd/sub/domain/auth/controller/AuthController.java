package com.dnd.sub.domain.auth.controller;

import com.dnd.sub.domain.auth.dto.KakaoTokenResponsed;
import com.dnd.sub.domain.auth.service.KakaoAuthService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final KakaoAuthService authService;

    @GetMapping("/login")
    public void redirectKakaoLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getCodeUrl());
    }

    @GetMapping("/callback")
    public ResponseEntity<KakaoTokenResponsed> getAccessToken(@RequestParam String code) {
        return ResponseEntity.ok().body(authService.getKakaoAccessToken(code));
    }

}
