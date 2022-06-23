package com.project.facegram.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

    private int id;
    private String loginId;
    private String memberPwd;
    private String memberName;
    private String email;
    private LocalDateTime joinDate;

}
