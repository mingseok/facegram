package com.project.facegram.mapper;

import com.project.facegram.domain.Profile;
import com.project.facegram.dto.ProfileDto;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsMapper {

    // Profile -> 반환값 , (int memberId) -> 매개변수
    Profile getMemberProfile(int memberId);

    void updateProfile(ProfileDto profileDto);

    // createProfile(member.getId()) 넘어옴.
    // 그 다음 xml 에서 디비에 있는 (member_id) 에 있는
    // 컬럼에 (#{memberId}) 을 저장 하는 것이다.
    // 이렇게 하여 프로필 생성 될때 해당 회원 id번호를 가져올수 있게 된 것이다.
    void createProfile(int memberId);
}
