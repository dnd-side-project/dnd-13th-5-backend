package com.dnd.sub.global.security.custom;

import com.dnd.sub.global.security.dto.KakaoAuthMemberDto;
import com.dnd.sub.global.security.dto.KakaoResponse;
import com.dnd.sub.domain.member.entity.Member;
import com.dnd.sub.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        if(!registrationId.equals("kakao")){
            throw new OAuth2AuthenticationException(new OAuth2Error("unsupported_provider", registrationId, null));
        }


        KakaoResponse kakaoResponse = new KakaoResponse(oAuth2User.getAttributes());
        String kakaoId = kakaoResponse.getKakaoId();
        String nickname = kakaoResponse.getNickname();
        String email = kakaoResponse.getEmail();

        Member member = memberRepository.findByKakaoId(kakaoId)
            .orElseGet(() -> createNewMember(kakaoResponse));  // 없으면 생성

        KakaoAuthMemberDto memberDto = KakaoAuthMemberDto.builder()
            .memberId(member.getId())
            .kakaoId(kakaoId)
            .email(email)
            .nickname(nickname).build();

        return new CustomOAuth2User(memberDto);


    }

    private Member createNewMember(KakaoResponse kakaoResponse) {

        Member member = Member.builder()
            .username(kakaoResponse.getNickname())
            .email(kakaoResponse.getEmail())
            .kakaoId(kakaoResponse.getKakaoId())
            .isNotificationOn(true)
            .build();

        return memberRepository.save(member);
    }
}
