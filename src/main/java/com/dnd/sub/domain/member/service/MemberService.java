package com.dnd.sub.domain.member.service;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.repository.MemberRepository;
import com.dnd.sub.global.enums.MemberErrorCode;
import com.dnd.sub.global.exception.MemberException;
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
}
