package com.dnd.sub.global.exception;

import com.dnd.sub.global.enums.GlobalErrorCode;
import com.dnd.sub.global.enums.TokenErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {
  private final TokenErrorCode errorCode;

  public TokenException(TokenErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

}
