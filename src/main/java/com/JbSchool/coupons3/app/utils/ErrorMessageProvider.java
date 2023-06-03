package com.JbSchool.coupons3.app.utils;

import lombok.*;

@Getter
@AllArgsConstructor

public enum ErrorMessageProvider {
  
  ID_MUST_BE_EMPTY("Id must be empty"),
  ID_NOT_FOUND("Id not found"),
  ID_ALREADY_EXIST("Id already exist"),
  EMAIL_ALREADY_EXIST("Email already exist"),
  BAD_CREDENTIALS("Bad Credentials"),
  
//  INCORRECT_REGISTRATION_DETAILS("Incorrect registration details"),
  
  // COMPANY,
  
  COMPANY_NAME_EXIST("Name already exist"),
  
  
  
  //  COUPON
  
  COUPON_NOT_OWNED_BY_COMPANY("Coupon is not owned by this company"),
  COUPON_TITLE_ALREADY_EXIST("Coupon title already exist"),
  COUPON_ALREADY_OWNED_BY_CUSTOMER("Coupon already owned by this customer"),
  COUPON_EXPIRED("Coupon expired"),
  OUT_OF_STOCK("Coupon out of stock"),
  
  
  //  CUSTOMER
  
  
  
  
  ;
  private final String message;
}
