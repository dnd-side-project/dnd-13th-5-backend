package com.dnd.sub.global.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

@UtilityClass
public class CookieUtil {

    public static ResponseCookie createCookie(String key, String value, int maxAge) {
        return ResponseCookie.from(key, value)
            .maxAge(maxAge)
            .sameSite("None")
            .path("/")
            .httpOnly(true)
            .secure(false)
            .build();
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        ResponseCookie cookie = ResponseCookie.from(name, value)
            .sameSite("Lax")
            .secure(true)
            .httpOnly(true)
            .path("/")
            .maxAge(maxAge)
            .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
