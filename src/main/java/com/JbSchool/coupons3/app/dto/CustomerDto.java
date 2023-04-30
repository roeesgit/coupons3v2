package com.JbSchool.coupons3.app.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDto {
  private String firsName;
  private String lastName;
  private String email;
  
}
