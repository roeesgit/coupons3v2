package com.JbSchool.coupons3.app.dto;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.security.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
@Component @RequiredArgsConstructor
public class Mapper {
  private final TokenConfig tokenConfig;
  private final CompanyRepo companyRepo;
  
  
  public UserDto companyToUserDto(Company company) {
    return UserDto.builder()
      .name(company.getName())
      .email(company.getEmail())
      .build();
  }
  
  
  public TokenDTO companyToTokenDTO(CouponUser couponUser) {
    return new TokenDTO(
      tokenConfig.generateToken(
        tokenConfig.buildClaims(
          couponUser
        )));
  }
  
  
  public CouponDto couponToCouponDto(Coupon coupon) {
    return CouponDto.builder()
      .title(coupon.getTitle())
      .description(coupon.getDescription())
      .category(coupon.getCategory())
      .amount(coupon.getAmount())
      .price(coupon.getPrice())
      .image(coupon.getImage())
      .endDate(coupon.getEndDate())
      .build();
  }
  
  
  public int companyIdFromSCH() {
    CouponUser couponUser = (CouponUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.companyRepo.findByEmail(couponUser.getUsername()).getId();
  }
  
  
}
