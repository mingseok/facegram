package com.project.facegram.service;

import com.project.facegram.domain.Comment;
import com.project.facegram.dto.CommentDto;
import com.project.facegram.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentMapper commentMapper;

    public void commentSave(String comment, int postId, String loginId) {
        //Dto를 만들어서 사용하는 것이다.
        CommentDto commentDto = CommentDto.builder()
                .content(comment)
                .postId(postId)
                .writer(loginId)
                .date(LocalDateTime.now())
                .build();

        commentMapper.saveComment(commentDto);
    }

    public List<Comment> getCommentList(int postId) {
        return commentMapper.getCommentList(postId);
    }

    public void commentDelete(int commentId) {
        commentMapper.deleteComment(commentId);
    }
}