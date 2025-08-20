package com.dnd.sub.domain.member.dto.response;

public record MemberInfoResponse(
    String email,
    String name,
    boolean isNotificaionOn
) {
}
