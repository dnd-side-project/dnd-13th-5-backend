package com.dnd.sub.domain.member.service;

import com.dnd.sub.domain.auth.repository.RefreshTokenRepository;
import com.dnd.sub.domain.member.dto.request.UpdateMemberRequest;
import com.dnd.sub.domain.member.dto.response.MemberInfoResponse;
import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.member.exception.MemberErrorCode;
import com.dnd.sub.domain.member.exception.MemberException;
import com.dnd.sub.domain.subscription.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SubscriptionService subscriptionService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional(readOnly = true)
    public Member findById(final Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(final Long memberId) {
        Member member = findById(memberId);
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }

    @Transactional
    public MemberInfoResponse updateMemberInfo(final Long memberId, final UpdateMemberRequest request) {
        Member member = findById(memberId);
        member.updateEmail(request.email());
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }

    @Transactional
    public MemberInfoResponse updateNotificationStatus(final Long memberId) {
        Member member = findById(memberId);
        member.updateIsNotificationOn();
        return new MemberInfoResponse(member.getEmail(), member.getName(),
            member.isNotificationOn());
    }

    @Transactional
    public void deleteMember(final Long memberId) {
        Member member = findById(memberId);

        deleteAllMemberSubscriptions(memberId);
        deleteRefreshToken(memberId);
        deleteMemberInfo(memberId);
    }

    private void deleteAllMemberSubscriptions(final Long memberId) {
        subscriptionService.deleteAllSubscriptions(memberId);
    }

    private void deleteMemberInfo(final Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private void deleteRefreshToken(final Long memberId) {
        refreshTokenRepository.deleteByMember_id(memberId);
    }
}
