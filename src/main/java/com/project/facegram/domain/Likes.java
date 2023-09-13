package com.project.facegram.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Likes {
    private int id;
    private int postId;
    private int memberId;
}