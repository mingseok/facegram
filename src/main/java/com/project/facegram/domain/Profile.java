package com.project.facegram.domain;

import lombok.Data;

@Data
public class Profile {

    private int id;
    private int memberId;
    private String bio; // 한줄 소개
    private String url; // 프로필 링크
    private String profileImage; // 프로필 이미지
}

