package com.project.facegram.service;

import com.project.facegram.domain.Member;
import com.project.facegram.domain.Profile;
import com.project.facegram.dto.ProfileDto;
import com.project.facegram.mapper.SettingsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettingsService {

    private final SettingsMapper settingsMapper;

    public Profile getMemberProfile(int memberId) {
        return settingsMapper.getMemberProfile(memberId);
    }

    public void createProfile(Member member) {
        settingsMapper.createProfile(member.getId());
    }

    public void updateProfile(ProfileDto profileDto, int memberId) {
        profileDto.setMemberId(memberId);
        settingsMapper.updateProfile(profileDto);
    }
}

