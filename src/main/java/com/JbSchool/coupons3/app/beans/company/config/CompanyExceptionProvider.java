package com.JbSchool.coupons3.app.beans.company.config;

import lombok.*;
@RequiredArgsConstructor @Getter
public enum CompanyExceptionProvider {
  INCORRECT_REGISTRATION_DETAILS("Incorrect registration details"),
  COMPANY_ID_ALREADY_EXIST("Company id already exist")
  
  ;
  private final String message;
}
