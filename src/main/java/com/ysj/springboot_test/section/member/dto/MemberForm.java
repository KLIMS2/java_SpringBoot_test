package com.ysj.springboot_test.section.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberForm
{
  private String username;
  private String password;
}
