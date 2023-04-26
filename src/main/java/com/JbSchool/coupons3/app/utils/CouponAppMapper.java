package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
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
  
   public Company dtoToCompany(UserDto userDto) {
    return  Company.builder()
      .name(userDto.getName())
      .email(userDto.getEmail())
      .build();
  }
  
  
  public UserDto companyToDto(Company company) {
    return UserDto.builder()
      .email(company.getEmail())
      .name(company.getName())
      .build();
  }
}
