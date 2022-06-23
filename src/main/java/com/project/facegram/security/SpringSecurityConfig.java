package com.project.facegram.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // 이미지 부분
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring() //보안필터를 적용할 필요가 없는 리소스 설정
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .mvcMatchers("/image/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // 인증을 사용하겠다 하는 부분.
                .mvcMatchers("/", "/sign-up", "/login") // 보안 필터 필요없는 경로, 모든사람이 들어갈수 있는 곳
                .permitAll(); // 허가 한다 할때.

        http.formLogin() // 로그인 인증 기능이 있는 메서드이다.
                .loginPage("/login") // 이렇게 등록하지 않으면 시큐리티가 자기가 직접 만든 로그인 화면을 보여주는 것이다.
                .defaultSuccessUrl("/")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder(); // 비밀번호를 암호화 알아서 해준다.
    }
}
