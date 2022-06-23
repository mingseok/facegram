package com.project.facegram.mapper;

import com.project.facegram.dto.ReplyDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {

    // 댓글 개수
    public int replyCount();

    // 댓글 목록
    public List<ReplyDto> replyList();

    // 댓글 작성
    public int insertReply(ReplyDto replyDto);

    // 댓글 수정
    public int updateReply(ReplyDto replyDto);

    // 댓글 삭제
    public int deleteReply(int replyId);

}
