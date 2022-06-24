package com.project.facegram.domain;

import lombok.Data;

@Data
public class Likes {

    private int id;
    private int postId;
    private int memberId;

}
