package com.project.facegram.controller;

import com.project.facegram.domain.Member;
import com.project.facegram.domain.Profile;
import com.project.facegram.dto.PostDto;
import com.project.facegram.dto.ProfileDto;
import com.project.facegram.service.MemberService;
import com.project.facegram.service.SettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
@Slf4j
public class SettingsController {

    private final SettingsService settingsService;
    private final MemberService memberService;

    @GetMapping("/profile")
    public String profileView(@AuthenticationPrincipal User user, // 로그인한 사용자의 정보를 파라메터로 받고 싶을때 사용
                              Model model) {

        Member member = memberService.getMember(user.getUsername());
        Profile profile = settingsService.getMemberProfile(member.getId());

        model.addAttribute("profileDto", new ProfileDto(profile));

        return "settings/profile";
    }


    @PutMapping("/profile")
    public String updateProfile(@ModelAttribute ProfileDto profileDto,
                                @AuthenticationPrincipal User user, // 로그인한 사용자의 정보를 파라메터로 받고 싶을때 사용
                                Model model){

        Member member = memberService.getMember(user.getUsername());
        settingsService.updateProfile(profileDto, member.getId());

        return "redirect:/profile";
    }
}