package com.dnd.sub.global.security.jwt;

import com.dnd.sub.domain.auth.exception.TokenErrorCode;
import com.dnd.sub.domain.auth.exception.TokenException;
import com.dnd.sub.global.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        TokenException tokenException = (TokenException) request.getAttribute("tokenException");

        ApiResponse<Void> errorResponse;
        if (tokenException != null) {
            errorResponse = ApiResponse.fail(tokenException.getErrorCode());
        } else {
            errorResponse = ApiResponse.fail(TokenErrorCode.INVALID_TOKEN);
        }

        String jsonResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonResponse);
    }
}
