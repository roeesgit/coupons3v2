package com.JbSchool.coupons3.app.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
  
  private int id;
  private String name;
  private String email;
  
}
