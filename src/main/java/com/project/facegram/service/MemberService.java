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


    // 추가 login_id = #{loginId}
    public Member getMember(String loginId) {
        return memberMapper.getMember(loginId);
    }


    public void joinMember(MemberDto memberDto) {

        // 여기서 현재시간으로 로직 수정 한것이다.
        memberDto.setJoinDate(LocalDateTime.now());

        memberDto.setMemberPwd(passwordEncoder.encode(memberDto.getMemberPwd()));

        memberMapper.joinMember(memberDto); // 디비에 보내는 코드이다.



        // 회원가입을 하면서 프로필을 바로 생성하는 것이다.
        // 그렇기 때문에 memberMapper.joinMember(memberDto); 회원 가입이 완료 된 후에
        // 이 코드를 작성 해야 하는 것이다. (= 밑에 적은 이유이다)

        // 순서.
        // 1. memberMapper.getMember() xml로 보낼건데 매개변수는
        // 2. memberDto.getLoginId() 가 되는 것이다. (= 타입은 String)
        // 3. 그리고 settingsService.createProfile() 메서드 안에 매개변수로는
        // 4. Member 객체가 된다.
        // 5. settingsService.createProfile(Member)
        settingsService.createProfile(memberMapper.getMember(memberDto.getLoginId()));
    }


    // 시큐리티 인터페이스 메서드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인 아이디 input 박스의 name=username 으로 되어 있으므로 그 username을 매개변수로 받는 것이다.

        Member member = memberMapper.getMember(username); // 로그인 아이디로 member를 찾음

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        // 객체 생성이랑 같은 부분이다. 즉 이거랑 같은것이다.
        //new User(member.getLoginId(), member.getMemberPwd(), "USER");
        return User.builder()
                .username(member.getLoginId())
                .password(member.getMemberPwd())
                .roles("USER") // 권한 이름인 것이다. 즉, 어드민일때랑 사용자일때 다른것 처럼 이런 느낌인 것이다.
                .build();
    }
}
