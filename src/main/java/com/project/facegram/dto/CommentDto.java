package com.project.facegram.dto;

import com.project.facegram.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentDto {

    private int id; // 댓글 일련번호
    private int postId; // 댓글이 속한 게시글의 고유 번호
    private String writer; // 댓글 작성자의 아이디
    private String content; // 댓글 내용
    private LocalDateTime date; // 댓글 작성 날짜

}
