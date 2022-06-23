package com.project.facegram.mapper;

import com.project.facegram.domain.Member;
import com.project.facegram.dto.MemberDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

    void joinMember(MemberDto memberDto);

    Member getMember(String loginId);

}
