package com.ysj.springboot_test.global.dto.rsData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RsData
{
  private String resultCode;
  private String msg;
  private Object data;

  public static RsData of(String resultCode, String msg)
  {
    return RsData.builder()
        .resultCode(resultCode)
        .msg(msg)
        .build();
  }

  public static RsData of(String resultCode, String msg, Object data)
  {
    return new RsData(resultCode, msg, data);
  }
}
