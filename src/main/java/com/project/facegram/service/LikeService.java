package com.project.facegram.service;

import com.project.facegram.domain.Likes;
import com.project.facegram.dto.LikeDto;
import com.project.facegram.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeMapper likeMapper;

    public void addLike(int postId, int memberId) {

        LikeDto likeDto = new LikeDto(postId, memberId);

        likeMapper.addLike(likeDto);
    }

    public void deleteLike(int postId, int memberId) {

        LikeDto likeDto = new LikeDto(postId, memberId);

        likeMapper.deleteLike(likeDto);
    }

    //게시글 좋아요 수 반환
    public int getLike(int postId) {
        return likeMapper.getLike(postId).size();
    }

    public boolean isLike(int postId, int memberId) {
        LikeDto likeDto = new LikeDto(postId, memberId);
        int res = likeMapper.isLike(likeDto);
        return res == 0 ? false : true;
    }
}
