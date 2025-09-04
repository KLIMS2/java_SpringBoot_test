package com.ysj.springboot_test.section.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member
{
  private static long lastId;
  private long id;
  private String username;
  private String password;

  static
  {
    lastId = 0;
  }

  public Member(String username, String password)
  {
    this(++lastId, username, password);
  }
}
