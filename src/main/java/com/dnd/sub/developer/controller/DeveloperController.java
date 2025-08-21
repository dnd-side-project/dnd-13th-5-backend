package com.dnd.sub.developer.controller;

import com.dnd.sub.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeveloperController {

    private final JwtProvider jwtProvider;

    //local 환경에서 테스트 시에만 사용
    @Profile("local")
    @GetMapping("/api-test/member/{memberId}")
    public String generateJwtToken(@PathVariable Long memberId) {
        return jwtProvider.generateToken(memberId);
    }
}
