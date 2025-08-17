package com.dnd.sub.global.security.jwt;

import com.dnd.sub.global.enums.TokenErrorCode;
import com.dnd.sub.global.exception.TokenException;
import com.dnd.sub.global.properties.JwtProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessExpiration;
    private final long refreshExpiration;

    public JwtProvider(JwtProperties jwtProperties) {
      this.secretKey = new SecretKeySpec(jwtProperties.secret().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
      this.accessExpiration = jwtProperties.accessExpiration();
      this.refreshExpiration = jwtProperties.refreshExpiration();
    }

    public String generateToken(Long userId) {
      return Jwts.builder()
          .claim("userId",userId)
          .issuedAt(new Date(System.currentTimeMillis()))
          .expiration(new Date(System.currentTimeMillis() + accessExpiration))
          .signWith(secretKey)
          .compact();
    }

  public String generateRefreshToken(Long userId) {
    return Jwts.builder()
        .claim("userId", userId)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
        .signWith(secretKey)
        .compact();
  }


    public Long extractUserId(String token){
        try{
          return Jwts.parser()
              .verifyWith(secretKey)
              .build()
              .parseSignedClaims(token)
              .getPayload()
              .get("userId", Long.class);
        }catch(ExpiredJwtException e){
          throw new TokenException(TokenErrorCode.EXPIRED_TOKEN);
        }catch(JwtException e){
          throw new TokenException(TokenErrorCode.INVALID_TOKEN);
        }
    }

}
