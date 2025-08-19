package com.dnd.sub.domain.auth.exception;

import com.dnd.sub.global.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {
  EXPIRED_TOKEN("TF-401", HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
  INVALID_TOKEN("TF-402", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
  NOT_FOUND_MEMBER("TF-403", HttpStatus.NOT_FOUND, "찾을수 없는 유저입니다."),
  REFRESH_NOT_FOUND("TF-404", HttpStatus.NOT_FOUND, "리프레시 토큰을 찾을 수 없습니다.");

  private final String code;
  private final HttpStatus status;
  private final String message;
}
