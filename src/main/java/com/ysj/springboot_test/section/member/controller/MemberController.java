package com.ysj.springboot_test.section.member.controller;

import com.ysj.springboot_test.global.dto.rsData.RsData;
import com.ysj.springboot_test.section.member.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/usr/member")
@Controller
public class MemberController
{
  @GetMapping("/login")
  @ResponseBody
  public RsData doLogin(@RequestParam(defaultValue = "null") String username, @RequestParam(defaultValue = "null") String password)
  {
    if(username == null)
    {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if(password == null)
    {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }

    Member member = new Member(username, password);

    return RsData.of("S-1", "%s님 회원 가입 완료되었습니다.".formatted(member.getUsername()), member);
  }
}
