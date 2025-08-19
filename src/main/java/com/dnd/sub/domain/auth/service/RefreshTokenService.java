package com.dnd.sub.domain.auth.service;

import com.dnd.sub.domain.auth.entity.RefreshToken;
import com.dnd.sub.domain.auth.repository.RefreshTokenRepository;
import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    @Transactional
    public void addRefresh(Long memberId, String refreshToken) {
        Member member = memberService.findById(memberId);
        Optional<RefreshToken> rt = refreshTokenRepository.findByMember(member);
        if (rt.isPresent()) {
            rt.get().updateRefreshToken(refreshToken);
        } else {
            RefreshToken token = RefreshToken.builder()
                .member(member)
                .refreshToken(refreshToken)
                .build();

            refreshTokenRepository.save(token);
        }
    }

    @Transactional
    public void removeRefresh(String refreshToken) {
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
    }

    @Transactional
    public void removeRefreshByMemberId(Long memberId) {
        Member member = memberService.findById(memberId);
        refreshTokenRepository.deleteByMember(member);
    }
}
