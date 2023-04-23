package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.company.facade.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;
@Component @RequiredArgsConstructor
public class CouponAppMapper {
  
  private final CouponUserRepo couponUserRepo;
  
  public CouponUser companyToCouponUser(Company company) {
    //todo: check if auth is null
    return this.couponUserRepo.findByUsername(company.getName());
  }
  
   public Company dtoToCompany(CompanyRespondDto companyRespondDto) {
    return  Company.builder()
      .name(companyRespondDto.getName())
      .email(companyRespondDto.getEmail())
      .build();
  }
  
  
  public CompanyRespondDto companyToDto(Company company) {
    return CompanyRespondDto.builder()
      .email(company.getEmail())
      .name(company.getName())
      .build();
  }
}
