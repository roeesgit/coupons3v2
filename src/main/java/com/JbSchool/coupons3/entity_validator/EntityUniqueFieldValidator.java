package com.JbSchool.coupons3.entity_validator;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.servlet.http.*;
import jakarta.validation.*;
import lombok.*;
import org.hibernate.validator.constraintvalidation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import java.util.*;
@RequiredArgsConstructor
public class EntityUniqueFieldValidator implements ConstraintValidator <EntityUniqueFieldConfig, Object> {
  
  private final CompanyRepo companyRepo;
  
  private final CustomerRepo customerRepo;
  
  private final CouponRepo couponRepo;
  private final Mapper     mapper;
  private       String     tableName;
  private       String     columnName;
  private boolean            isUpdate;
  @Autowired
  private HttpServletRequest request;
  
  @Override
  public void initialize(EntityUniqueFieldConfig constraintAnnotation) {
    this.tableName = constraintAnnotation.tableName();
    this.columnName = constraintAnnotation.columnName();
    this.isUpdate =  request.getMethod().equals(HttpMethod.PUT.toString());
  }
  
  
  
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    System.out.println(request.getPathInfo());
     // TODO: 11/22/2022 test
           System.out.println("********* Test 1 **********");
           System.out.println();
           System.out.println("********* Test 2 **********");
           System.out.println();
request.getParameterMap().forEach((k,v)-> System.out.println("key: "+k+"\nvalue: "+ Arrays.toString(v)));
   
    Company company =context.unwrap(HibernateConstraintValidatorContext.class)
      .getConstraintValidatorPayload(Company.class);
    System.out.println("HibernateConstraintValidatorContext"+ HibernateConstraintValidatorContext.class);
    int SCHid = this.mapper.userIdFromSCH();
    switch (this.tableName) {
    case "companies":
      switch (this.columnName) {
      case "email":
        return !this.companyRepo.existsByEmailAndIdNot(value.toString(), SCHid);
      case "name":
        return !this.companyRepo.existsByNameAndIdNot(value.toString(), SCHid);
      case "id":
        // להוציא לפונקציה
        return !isUpdate || this.companyRepo.existsById(Integer.parseInt(value.toString()));
      }
      break;
    case "customers":
      switch (this.columnName) {
      case "email":
        return !this.customerRepo.existsByEmailAndIdNot(value.toString(), SCHid);
      case "id":
        return !isUpdate||this.customerRepo.existsById(Integer.parseInt(value.toString()));
      }
    case "coupons":
      switch (this.columnName) {
      case "title":
        return !this.couponRepo.existsByTitleAndCompanyId(value.toString(), SCHid);
      case "id":
        return !isUpdate && this.couponRepo.existsById(Integer.parseInt(value.toString()));
      }
      
    }
    return false;
  }
  
  
  
  
}