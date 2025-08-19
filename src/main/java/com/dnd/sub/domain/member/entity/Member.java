package com.dnd.sub.domain.member.entity;

import com.dnd.sub.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "kakao_id", length = 200, nullable = false, unique = true)
    private String kakaoId;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean isNotificationOn = true;

    public void updateIsNotificationOn() {
      this.isNotificationOn = !this.isNotificationOn;
  }

  @Builder
  public Member(
      final String name,
      final String email,
      final String kakaoId
  ) {
      this.name = name;
      this.email = email;
      this.kakaoId = kakaoId;
  }
}
