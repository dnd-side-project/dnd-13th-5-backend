//package com.dnd.sub.global.config;
//
//import java.util.Arrays;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configurable
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//  @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .cors(cors -> cors.configurationSource(configurationSource()))
//        .csrf(AbstractHttpConfigurer::disable)
//        .formLogin(AbstractHttpConfigurer::disable)
//        .httpBasic(AbstractHttpConfigurer::disable)
//        .sessionManagement(s -> s.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
//        .authorizeHttpRequests(
//            a -> a.requestMatchers("/").permitAll(); //.anyRequest().authenticated());
/// /
//    return http.build();
//  }
//
//  @Bean
//  public CorsConfigurationSource configurationSource() {
//    CorsConfiguration configuration = new CorsConfiguration();
//    configuration.setAllowedOriginPatterns(List.of("*"));
//    configuration.setAllowedMethods(List.of("*"));
//    configuration.setAllowedHeaders(List.of("*"));
//    configuration.setAllowCredentials(true);
//
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", configuration);
//    return source;
//  }
//}
