package com.dnd.sub.global.config;

import com.dnd.sub.global.security.custom.CustomOAuth2UserService;
import com.dnd.sub.global.security.handler.CustomSuccessHandler;
import com.dnd.sub.global.security.jwt.JwtProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configurable
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomSuccessHandler customSuccessHandler;
  private final JwtProvider jwtProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(configurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .oauth2Login((oauth2) -> oauth2
            .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                .userService(customOAuth2UserService))
            .successHandler(customSuccessHandler))
        .sessionManagement(s -> s.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
        .authorizeHttpRequests(
            a -> a.anyRequest().permitAll() //일단 다 허용
        );

    return http.build();
  }

  @Bean
  public CorsConfigurationSource configurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
