package com.dnd.sub.domain.auth.repository;

import com.dnd.sub.domain.auth.entity.RefreshToken;
import com.dnd.sub.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByRefreshToken(String refreshToken);

    void deleteByMember(Member member);

    Optional<RefreshToken> findByMember(Member member);

    void deleteByMember_id(Long memberId);
}
