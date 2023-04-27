package com.JbSchool.coupons3.entity_validator;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;

import java.util.*;
import java.util.stream.*;
public class EntityUniqueFieldValidator implements ConstraintValidator <EntityUniqueFieldConfig, Object> {
  
  @Autowired
  private CompanyRepo  companyRepo;
  @Autowired
  private CustomerRepo customerRepo;
  @Autowired
  private CouponRepo   couponRepo;
  private String       tableName;
  private String       columnName;
  
  
  @Override
  public void initialize(EntityUniqueFieldConfig constraintAnnotation) {
    this.tableName = constraintAnnotation.tableName();
    this.columnName = constraintAnnotation.columnName();
  }
  
  
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    int SCHid =0 ;
      List < String > authNames = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
     if (authNames.contains("ROLE_COMPANY")) {
      Company company = this.companyRepo.findByEmail(((CouponUser)
        SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
      SCHid = company.getId();
    } else if (authNames.contains("ROLE_CUSTOMER")) {
    Customer customer = this.customerRepo.findByEmail(((CouponUser)
      SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
      SCHid = customer.getId();
  }
     // TODO: 11/22/2022 test
           System.out.println("********* Test 1 **********");
    authNames.forEach(System.out::println);
    System.out.println();
    System.out.println("********* Test 2 **********");
    System.out.println();
    switch (this.tableName) {
    case "companies":
      System.out.println("********* Test 1 **********");
      switch (this.columnName) {
      case "email":
        return !this.companyRepo.existsByEmailAndIdNot(value.toString(), SCHid);
      case "name":
        return !this.companyRepo.existsByName(value.toString());
      case "id":
        return !this.companyRepo.existsById(Integer.valueOf(value.toString()));
      }
      break;
    case "customers":
      switch (this.columnName) {
      case "email":
        return !this.customerRepo.existsByEmailAndIdNot(value.toString(), SCHid);
      case "id":
        return !this.customerRepo.existsById(Integer.valueOf(value.toString()));
      }
    case "coupons":
      CouponUser couponUser = (CouponUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      
      return this.couponRepo.existsByTitleAndCompanyIdNot(value.toString(), couponUser.getId());
    }
    return false;
  }
  
  
  
  
}