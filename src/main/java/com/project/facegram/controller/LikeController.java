package com.project.facegram.controller;

import com.project.facegram.domain.Member;
import com.project.facegram.domain.Post;
import com.project.facegram.service.LikeService;
import com.project.facegram.service.MemberService;
import com.project.facegram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final MemberService memberService;


    @GetMapping("/like/add")
    @ResponseBody
    public int addLike(int id, @AuthenticationPrincipal User user) {

        Member member = memberService.getMember(user.getUsername());
        likeService.addLike(id, member.getId());

        // getLike(id) 는
        // FOREIGN KEY (post_id) REFERENCES post (id) 로 되어 있다.
        // 리턴 하면 게시판의 id인 것이다.
        // 즉, @ResponseBody 어너테이션이 붙어 있으므로,
        // 바로 화면에 출력 되는 것이다.
        return likeService.getLike(id);
    }

    @GetMapping("/like/remove")
    @ResponseBody
    public int removeLike(int id, @AuthenticationPrincipal User user) {

        Member member = memberService.getMember(user.getUsername());
        likeService.deleteLike(id, member.getId());

        return likeService.getLike(id);
    }
}
