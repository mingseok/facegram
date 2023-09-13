package com.project.facegram.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private int id;
    private String loginId;
    private String memberPwd;
    private String memberName;
    private String email;
    private LocalDateTime joinDate;
}