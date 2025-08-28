package com.dnd.sub.global.config;

import com.dnd.sub.global.security.custom.CustomOAuth2UserService;
import com.dnd.sub.global.security.handler.CustomLogoutSuccessHandler;
import com.dnd.sub.global.security.handler.CustomOAuth2LogoutHandler;
import com.dnd.sub.global.security.handler.CustomSuccessHandler;
import com.dnd.sub.global.security.jwt.JwtAuthenticationEntryPoint;
import com.dnd.sub.global.security.jwt.JwtFilter;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2LogoutHandler customOAuth2LogoutHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtFilter jwtFilter;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomOAuth2LogoutHandler customOAuth2LogoutHandler) throws Exception {
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
              .exceptionHandling(exceptions -> exceptions
                      .authenticationEntryPoint(jwtAuthenticationEntryPoint)
              )
              .logout(logout -> logout
                      .logoutUrl("/api/auth/logout")
                      .addLogoutHandler(customOAuth2LogoutHandler)
                      .deleteCookies("refresh_token")
                      .logoutSuccessHandler(customLogoutSuccessHandler)
              )
          .authorizeHttpRequests(
              a ->
                  a.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                      .requestMatchers("/api/member/**",
                          "/api/subscriptions/**",
                          "/api/products/**").authenticated()
              .anyRequest().permitAll() //일단 다 허용
          )
          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

      return http.build();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(List.of(    "http://localhost:3000",
          "http://localhost:5173, https://www.waguwagu.site", "https://waguwagu.site"));
      configuration.setAllowedMethods(List.of("*"));
      configuration.setAllowedHeaders(List.of("*"));
      configuration.setExposedHeaders(List.of("Authorization", "Set-Cookie"));
      configuration.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
}
