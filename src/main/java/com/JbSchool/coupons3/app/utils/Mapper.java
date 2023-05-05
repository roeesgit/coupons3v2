package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.beans.security.auth.*;
import com.JbSchool.coupons3.app.beans.security.entites.users.*;
import com.JbSchool.coupons3.app.beans.security.token.*;
import com.JbSchool.coupons3.app.dto.*;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;
@Component
@RequiredArgsConstructor
public class Mapper {
  
  private final TokenConfig tokenConfig;
  private final CompanyRepo companyRepo;
  private final CustomerRepo customerRepo;
  
  
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
      .id(coupon.getId())
      .title(coupon.getTitle())
      .description(coupon.getDescription())
      .category(coupon.getCategory())
      .amount(coupon.getAmount())
      .price(coupon.getPrice())
      .image(coupon.getImage())
      .endDate(coupon.getEndDate())
      .build();
  }
  
  
  public int userIdFromSCH() {
    CouponUser couponUser = (CouponUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List <String> authNames = SecurityContextHolder.getContext().getAuthentication()
      .getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    int SCHid = 0; // 0 for admin
    
    if (authNames.contains("ROLE_COMPANY")) {
      SCHid = this.companyRepo.findByEmail(couponUser.getUsername()).getId();
    } else if (authNames.contains("ROLE_CUSTOMER")) {
      SCHid = this.customerRepo.findByEmail(couponUser.getUsername()).getId();
    }
    return SCHid;
  }
  
  
  public UserDto customerToUserDto(Customer customer) {
    return UserDto.builder()
      .name(customer.getFirstName() + " "+ customer.getLastName())
      .email(customer.getEmail())
      .build();
  }
  
  
  public List<UserDto> companyListToUserDtoList(List<Company> companies) {
   return companies.stream().map(this::companyToUserDto).collect(Collectors.toList());
  }
  
  
  public List<UserDto> customerListToUserDtoList(List<Customer> customers) {
    return customers.stream().map(this::customerToUserDto).collect(Collectors.toList());
  
  }
  
  
}
