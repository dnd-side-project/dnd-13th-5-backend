package com.dnd.sub.domain.auth.repository;

import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.auth.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByToken(String refreshToken);
    void deleteByMember(Member member);

    Optional<RefreshToken> findByMember(Member member);
}
