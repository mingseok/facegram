package com.project.facegram.controller;

import com.project.facegram.domain.Comment;
import com.project.facegram.domain.Member;
import com.project.facegram.domain.Post;
import com.project.facegram.domain.Profile;
import com.project.facegram.service.CommentService;
import com.project.facegram.service.MemberService;
import com.project.facegram.service.PostService;
import com.project.facegram.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CommentController {

    private final MemberService memberService;
    private final CommentService commentService;
    private final PostService postService;
    private final SettingsService settingsService;

    // 설명
    // posts/post-view 페이지로 보내고
    // :: #comment-list 부분은 post-view.html 파일의
    // <div class="comments col-9 comment-list" style="padding-top: 3%;" id="comment-list">
    // 으로 가게 되는 것이다.
    private static final String COMMENT_PATH = "posts/post-view :: #comment-list";

    //댓글 저장
    @PostMapping("/comment/save/{postId}")
    public String commentSave(@PathVariable Long postId,
                              @RequestParam String comment,
                              @AuthenticationPrincipal User user,
                              Model model) {


        Post post = postService.getPost(postId);

        // 현재 로그인한 멤버
        Member loginMember = memberService.getMember(user.getUsername());

        commentService.commentSave(comment, postId, user.getUsername());

        // 현재 로그인한 사람의 프로필 가져오기
        Profile profile = settingsService.getMemberProfile(loginMember.getId());

        // 현재 게시글의 댓글 가져오기
        List<Comment> comments = commentService.getCommentList(postId);

        model.addAttribute("profile", profile); //프로필 관련 보여줄 부분

        model.addAttribute("comments", comments);
        model.addAttribute("member", loginMember);
        model.addAttribute("post", post);

        return COMMENT_PATH; //모델에 담아서 보냄.
    }

    //댓글 삭제
    @PostMapping("/deleteComment/{commentId}/{postId}")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable int commentId,
                                @AuthenticationPrincipal User user,
                                Model model) {


        Post post = postService.getPost(postId);
        Member loginMember = memberService.getMember(user.getUsername()); //현재 로그인한 멤버
        Profile profile = settingsService.getMemberProfile(loginMember.getId());

        commentService.commentDelete(commentId); //댓글 삭제

        //삭제 후에 다시 댓글 가져오기
        List<Comment> comments = commentService.getCommentList(postId); //현재 게시글의 댓글 가져오기

        model.addAttribute("profile", profile); //프로필 관련 보여줄 부분
        model.addAttribute("comments", comments);
        model.addAttribute("member", loginMember);
        model.addAttribute("post", post);

        return COMMENT_PATH;
    }

}
