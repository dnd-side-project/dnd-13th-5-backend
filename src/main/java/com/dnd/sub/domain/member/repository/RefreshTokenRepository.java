package com.dnd.sub.domain.member.repository;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByRefreshToken(String refreshToken);
    void deleteByMember(Member member);
}
