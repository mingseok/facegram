package com.project.facegram.mapper;

import com.project.facegram.domain.Post;
import com.project.facegram.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    List<Post> postList();

    Post getPost(Long id);

    void savePost(PostDto postDto);

    void updatePost(PostDto postDto);

    void deletePost(Long id);

    void increaseHit(Long id);
}
