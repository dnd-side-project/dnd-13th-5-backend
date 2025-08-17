package com.dnd.sub.domain.member.service;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.entity.RefreshToken;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.domain.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    @Transactional
    public void addRefresh(Long memberId, String refreshToken) {
        Member member = memberService.findById(memberId);

        RefreshToken token = RefreshToken.builder()
            .member(member)
            .token(refreshToken)
            .build();

        refreshTokenRepository.save(token);
    }

    @Transactional
    public void removeRefresh(String refreshToken) {
        refreshTokenRepository.deleteByToken(refreshToken);
    }

    @Transactional
    public void removeRefreshByMemberId(Long memberId) {
        Member member = memberService.findById(memberId);
        refreshTokenRepository.deleteByMember(member);
    }
}
