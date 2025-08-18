package com.dnd.sub.domain.member.entity;

import com.dnd.sub.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  private String username;

  @Column(length = 200, nullable = false, unique = true)
  private String email;

  @Column(length = 100, nullable = false)
  private String kakaoId;

  @Column(columnDefinition = "TINYINT(1)")
  private boolean isNotificationOn = true;

  public void updateNotificationOn() {
    this.isNotificationOn = !this.isNotificationOn;
  }

}
