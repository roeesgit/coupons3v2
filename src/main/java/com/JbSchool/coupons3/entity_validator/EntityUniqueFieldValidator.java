package com.JbSchool.coupons3.entity_validator;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.dto.*;
import jakarta.validation.*;
import lombok.*;
@RequiredArgsConstructor
public class EntityUniqueFieldValidator implements ConstraintValidator <EntityUniqueFieldConfig, Object> {
  
  
  private final CompanyRepo companyRepo;
  
  private final CustomerRepo customerRepo;
  
  private final CouponRepo couponRepo;
  private final Mapper     mapper;
  private       String     tableName;
  private       String     columnName;
  
  
  @Override
  public void initialize(EntityUniqueFieldConfig constraintAnnotation) {
    this.tableName = constraintAnnotation.tableName();
    this.columnName = constraintAnnotation.columnName();
  }
  
  
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    int SCHid = this.mapper.userIdFromSCH();
    switch (this.tableName) {
    case "companies":
      switch (this.columnName) {
      case "email":
        return (this.companyRepo.existsByEmailAndNotEmail(value.toString())==0);
//        return !this.companyRepo.existsByEmailAndIdNot(value.toString(), SCHid);
      case "name":
        return (this.companyRepo.existsByNameAndNameNot(value.toString())==0);
      case "id":
        return !this.companyRepo.existsById(Integer.parseInt(value.toString()));
      }
      break;
    case "customers":
      switch (this.columnName) {
      case "email":
        return (this.customerRepo.existsByEmailAndNotEmail(value.toString(), SCHid)==0);
      case "id":
        return Integer.parseInt(value.toString()) < 1;
      }
    case "coupons":
      switch (this.columnName) {
      case "title":
       
        return !this.couponRepo.existsByTitleAndCompanyId(value.toString(), SCHid);
      case "id":
        return Integer.parseInt(value.toString()) < 1;
      }
      
    }
    return false;
  }
  
  
  
  
}