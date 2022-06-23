package com.project.facegram.service;

import com.project.facegram.domain.Post;
import com.project.facegram.dto.PostDto;
import com.project.facegram.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostMapper postMapper;

    public List<Post> postList() {
        return postMapper.postList(); // 게시글 리스트 반환
    }

    public Post getPost(Long id) {
        return postMapper.getPost(id);
    }

    public void savePost(PostDto postDto, String userId) {
        postDto.setCreatedDate(LocalDateTime.now());
        postDto.setWriter(userId);
        postMapper.savePost(postDto);
    }

    @Transactional
    public void updatePost(PostDto postDto) {
        postMapper.updatePost(postDto);
    }

    @Transactional
    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }

    public void increaseHit(Long id) {
        postMapper.increaseHit(id);
    }
}
