package com.dnd.sub.domain.member.repository;

import com.dnd.sub.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByKakaoId(String kakaoId);
}
