package com.project.facegram.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private int id;
    private String loginId;
    private String memberPwd;
    private String memberName;
    private String email;
    private LocalDateTime joinDate;

    public void changeJoinDate() {
        this.joinDate = LocalDateTime.now();
    }

    public void changeMemberPwd(String password) {
        this.memberPwd = password;
    }

}