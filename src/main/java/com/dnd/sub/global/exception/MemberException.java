package com.dnd.sub.global.exception;


import com.dnd.sub.global.enums.MemberErrorCode;

public class MemberException extends RuntimeException {

    private final MemberErrorCode errorCode;

    public MemberException(MemberErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
