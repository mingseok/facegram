package com.project.facegram.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDto {

    private int postId;
    private int memberId;


    public LikeDto(int postId, int memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }
}
