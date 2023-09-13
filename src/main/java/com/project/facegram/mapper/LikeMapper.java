package com.project.facegram.mapper;

import com.project.facegram.domain.Likes;
import com.project.facegram.dto.LikeDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeMapper {

    void addLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);

    List<Likes> getLike(int postId);

    int isLike(LikeDto likeDto);
}