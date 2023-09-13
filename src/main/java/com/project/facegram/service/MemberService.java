package com.project.facegram.service;

import com.project.facegram.domain.Member;
import com.project.facegram.dto.MemberDto;
import com.project.facegram.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService implements UserDetailsService { // 시큐리티 사용하기 위한 인터페이스

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final SettingsService settingsService;

    public Member getMember(String loginId) {
        return memberMapper.getMember(loginId);
    }

    public void joinMember(MemberDto memberDto) {
        memberDto.changeJoinDate(); // 현재시간으로

        memberDto.changeMemberPwd(passwordEncoder.encode(memberDto.getMemberPwd()));

        memberMapper.joinMember(memberDto); // 디비에 보내는 코드이다.

        settingsService.createProfile(memberMapper.getMember(memberDto.getLoginId()));
    }

    // 시큐리티 인터페이스 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.getMember(username); // 로그인 아이디로 member를 찾음

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        // 객체 생성이랑 같은 부분이다. 즉 밑에 주석 코드랑 같은것이다
        //new User(member.getLoginId(), member.getMemberPwd(), "USER");
        return User.builder()
                .username(member.getLoginId())
                .password(member.getMemberPwd())
                .roles("USER") // 권한 이름인 것이다. 즉, 어드민일때랑 사용자일때 다른것처럼
                .build();
    }
}