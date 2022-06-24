package com.project.facegram.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private int hit;
}
