package com.project.facegram.mapper;

import com.project.facegram.domain.Comment;
import com.project.facegram.domain.Post;
import com.project.facegram.dto.CommentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {

    // 댓글 개수
    public int commentCount();

    // 댓글 목록
    public List<Comment> getCommentList(Long postId);

    // 댓글 작성
    public int saveComment(CommentDto commentDto);

    // 댓글 삭제
    public int deleteComment(int commentId);

}
