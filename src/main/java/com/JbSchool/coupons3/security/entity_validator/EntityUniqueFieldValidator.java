package com.JbSchool.coupons3.security.entity_validator;

import com.JbSchool.coupons3.app.beans.company.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
public class EntityUniqueFieldValidator implements ConstraintValidator <EntityUniqueFieldConfig, Object> {

  @Autowired
  private CompanyRepo  companyRepo;
  @Autowired
  private CustomerRepo customerRepo;
 @Autowired
  private CouponRepo   couponRepo;
  private String       tableName;
  private       String      columnName;


  @Override
  public void initialize(EntityUniqueFieldConfig constraintAnnotation) {
    this.tableName = constraintAnnotation.tableName();
    this.columnName = constraintAnnotation.columnName();
  }


  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    switch (this.tableName) {
    case "companies":
      System.out.println("********* Test 1 **********");
      Company company = this.companyRepo.findByEmail(((CouponUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
      if (this.columnName.equals("email")) {
      return !this.companyRepo.existsByEmailAndIdNot(value.toString(),company.getId());
    } else if (this.columnName.equals("name")) {
      return !this.companyRepo.existsByName(value.toString());
    }
      break;
    case "customers":
      return this.customerRepo.existsByEmail(value.toString());
    case "coupons":
      CouponUser couponUser = (CouponUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      return this.couponRepo.existsByTitleAndCompanyIdNot(value.toString(), couponUser.getId());
    }
    return false;
  }




}