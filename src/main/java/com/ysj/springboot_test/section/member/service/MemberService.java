package com.ysj.springboot_test.section.member.service;

import com.ysj.springboot_test.global.dto.rsData.RsData;
import com.ysj.springboot_test.section.member.dto.MemberForm;
import com.ysj.springboot_test.section.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService
{
  //  private final MemberRepository memberRepository;

  public RsData join(MemberForm memberForm)
  {
    Member member = new Member(memberForm.getUsername(), memberForm.getPassword());

    return RsData.of("S-1", "%s님 회원가입되었습니다.".formatted(member.getUsername()), member);
  }
}
