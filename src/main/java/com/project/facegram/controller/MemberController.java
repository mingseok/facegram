package com.project.facegram.controller;

import com.project.facegram.dto.MemberDto;
import com.project.facegram.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/main")
    public String main() {
        return "members/main";
    }

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute("memberDto", new MemberDto()); // 그릇도 같이 보내는 것이다.
        return "members/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute MemberDto memberDto) {
        memberService.joinMember(memberDto);
        return "redirect:/main";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false, value = "error") String error,
                        @RequestParam(required = false, value = "exception") String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/members/login";
    }
}