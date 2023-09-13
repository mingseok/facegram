package com.project.facegram.dto;

import com.project.facegram.domain.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {

    private int memberId;

    private String bio; // 한줄 소개

    private String url; // 프로필 링크

    private String profileImage; // 프로필 이미지

    // 처음 딱 프로필을 들어간다면, memberId 만 출력된다. (나머지는 없다)
    // 그리고 프로필을 나갔다가 들어오면 아까 프로필에서 저장 시킨것들을
    // 출력하고 싶기 때문에 이렇게 사용했다.
    public ProfileDto(Profile profile) {
        this.memberId = profile.getMemberId();
        this.bio = profile.getBio();
        this.url = profile.getUrl();
        this.profileImage = profile.getProfileImage();
    }
}