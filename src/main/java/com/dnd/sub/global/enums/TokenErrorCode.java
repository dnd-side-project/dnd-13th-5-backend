package com.dnd.sub.global.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TokenErrorCode implements ErrorCode {
  EXPIRED_TOKEN("ATF-401", HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
  INVALID_TOKEN("ATF-402", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
  NOT_FOUND_USER("ATF-403", HttpStatus.NOT_FOUND, "찾을수 없는 유저입니다.");

  private final String code;
  private final HttpStatus status;
  private final String message;
}
