package com.project.facegram.controller;

import com.project.facegram.domain.Member;
import com.project.facegram.domain.Post;
import com.project.facegram.domain.Profile;
import com.project.facegram.dto.PostDto;
import com.project.facegram.service.MemberService;
import com.project.facegram.service.PostService;
import com.project.facegram.service.SettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final SettingsService settingsService;

    // 게시판 전체 출력
    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("list", postService.postList());
        return "posts/post-list";
    }

    // 게시판 상세 보기
    @GetMapping("/post-view")
    public String viewPost(Model model, @RequestParam Long id, @AuthenticationPrincipal User user) {

        Member member = memberService.getMember(user.getUsername());
        Profile profile = settingsService.getMemberProfile(member.getId());

        model.addAttribute("profile",profile); //프로필 관련 보여줄 부분

        postService.increaseHit(id); //조회수 1 증가 (DB에)
        Post post = postService.getPost(id); //조회수 update 된 post 가져오기

        model.addAttribute("post", post);

        return "posts/post-view";
    }

    // 게시글 작성
    @GetMapping("/new-post")
    public String newPostForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "posts/new-post";
    }

    // 게시글 저장
    @PostMapping("/new-post")
    public String newPost(PostDto postDto, @AuthenticationPrincipal User user) {
        postService.savePost(postDto, user.getUsername());
        return "redirect:/post";
    }

    //게시글 수정 폼
    @GetMapping("/update/{id}")
    public String updatePostForm(Model model, @PathVariable Long id) {

        model.addAttribute("postDto", new PostDto());
        model.addAttribute("post", postService.getPost(id));

        return "posts/update-post";
    }

    @PutMapping("/update")
    public String updatePost(@ModelAttribute PostDto postDto) {
        postService.updatePost(postDto);
        // 업데이트만 했는데, 어떻게 다시 디비에서 꺼내 오는가?? "난 수정만 했을뿐인데?"
        // 그 이유는 바로 밑에 return redirect 로 /post를 실행 시키기 때문인 것이다.
        // 그러면 다시 postService.postList() 가 실행 되는 것이다.
        return "redirect:/post";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "/post";
    }
}

