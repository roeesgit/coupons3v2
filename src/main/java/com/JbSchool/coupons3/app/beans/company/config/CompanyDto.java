package com.JbSchool.coupons3.app.beans.company.config;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyDto {
  
  private int id;
  private String name;
  private String email;
  
}
