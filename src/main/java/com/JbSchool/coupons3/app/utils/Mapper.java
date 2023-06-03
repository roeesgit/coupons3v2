package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


/**
 Utility class for mapping entities to DTOs and performing other mapping operations.
 */
@Component
@RequiredArgsConstructor
public class Mapper {
  
  private final TokenConfig  tokenConfig;
  private final CompanyRepo  companyRepo;
  private final CustomerRepo customerRepo;
  
  
  /**
   Converts a Company entity to a CompanyDto.
   
   @param company the Company entity to be converted
   @return the corresponding CompanyDto
   */
  public CompanyDto companyToCompanyDto(Company company) {
    return CompanyDto.builder()
      .id(company.getId())
      .name(company.getName())
      .email(company.getEmail())
      .build();
  }
  
  
  /**
   Converts a list of Coupon entities to a list of CouponDto objects.
   
   @param coupons the list of Coupon entities to be converted
   @return the corresponding list of CouponDto objects
   */
  public List <CouponDto> couponsToCouponsDto(List <Coupon> coupons) {
    return coupons.stream().map(this::couponToCouponDto).collect(Collectors.toList());
  }

//  /**
//   * Converts a CouponUser (Company or Customer) to a TokenDTO containing the generated token.
//   *
//   * @param couponUser the CouponUser object for which the token is generated
//   * @return the TokenDTO containing the generated token
//   */
//  public TokenDTO companyToTokenDTO(CouponUser couponUser) {
//    return new TokenDTO(
//      tokenConfig.generateToken(
//        tokenConfig.buildClaims(
//          couponUser
//        )));
//  }
//
  
  
  /**
   Converts a Coupon entity to a CouponDto.
   
   @param coupon the Coupon entity to be converted
   @return the corresponding CouponDto
   */
  public CouponDto couponToCouponDto(Coupon coupon) {
    return CouponDto.builder()
      .id(coupon.getId())
      .category(coupon.getCategory())
      .title(coupon.getTitle())
      .description(coupon.getDescription())
      .startDate(coupon.getStartDate())
      .endDate(coupon.getEndDate())
      .amount(coupon.getAmount())
      .price(coupon.getPrice())
      .image(coupon.getImage())
      .companyId(coupon.getCompanyId())
      .build();
  }
  
  
  /**
   Retrieves the user ID from the SecurityContextHolder.
   
   @return the user ID
   */
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
  
  
  /**
   Converts a Customer entity to a CustomerDto.
   
   @param customer the Customer entity to be converted
   @return the corresponding CustomerDto
   */
  public CustomerDto customerToCustomerDto(Customer customer) {
    return CustomerDto.builder()
      .id(customer.getId())
      .firstName(customer.getFirstName())
      .lastName(customer.getLastName())
      .email(customer.getEmail())
      .build();
  }
  
  
  /**
   Converts a list of Company entities to a list of CompanyDto objects.
   
   @param companies the list of Company entities to be converted
   @return the corresponding list of CompanyDto objects
   */
  public List <CompanyDto> companyListToUserDtoList(List <Company> companies) {
    return companies.stream().map(this::companyToCompanyDto).collect(Collectors.toList());
  }
  
  
  /**
   Converts a list of Customer entities to a list of CustomerDto objects.
   
   @param customers the list of Customer entities to be converted
   @return the corresponding list of CustomerDto objects
   */
  public List <CustomerDto> customerListToCustomerDtoList(List <Customer> customers) {
    return customers.stream().map(this::customerToCustomerDto).collect(Collectors.toList());
    
  }
  
  
}
