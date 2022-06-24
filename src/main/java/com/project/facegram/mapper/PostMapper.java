package com.project.facegram.mapper;

import com.project.facegram.domain.Post;
import com.project.facegram.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMapper {

    List<Post> postList();

    Post getPost(int id);

    void savePost(PostDto postDto);

    void updatePost(PostDto postDto);

    void deletePost(int id);

    void increaseHit(int id);
}
