package com.dnd.sub.domain.auth.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {
  EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "TF-401", "만료된 토큰입니다."),
  INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "TF-402", "유효하지 않은 토큰입니다."),
  NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), "TF-403", "찾을수 없는 유저입니다."),
  REFRESH_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "TF-404", "리프레시 토큰을 찾을 수 없습니다.");

  private final int status;
  private final String code;
  private final String message;
}
