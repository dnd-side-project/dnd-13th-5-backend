package com.dnd.sub.domain.member.dto.request;

import jakarta.validation.constraints.Email;

public record UpdateMemberRequest(@Email(message = "올바르지 않은 이메일 입니다.") String email) {
}
