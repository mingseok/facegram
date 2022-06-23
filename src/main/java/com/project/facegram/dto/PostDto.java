package com.project.facegram.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private Long hit;
}
