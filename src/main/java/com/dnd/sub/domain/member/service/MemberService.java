package com.dnd.sub.domain.member.service;

import com.dnd.sub.domain.member.dto.request.UpdateMemberRequest;
import com.dnd.sub.domain.member.dto.response.MemberInfoResponse;
import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.member.exception.MemberErrorCode;
import com.dnd.sub.domain.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = findById(memberId);
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }

    @Transactional
    public MemberInfoResponse updateMemberInfo(Long memberId, UpdateMemberRequest request) {
        Member member = findById(memberId);
        member.updateEmail(request.email());
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }

    @Transactional
    public MemberInfoResponse updateNotificationOn(Long memberId) {
        Member member = findById(memberId);
        member.updateIsNotificationOn();
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }
}
