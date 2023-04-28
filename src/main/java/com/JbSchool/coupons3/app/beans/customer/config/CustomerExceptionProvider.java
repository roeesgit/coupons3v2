package com.JbSchool.coupons3.app.beans.customer.config;

import lombok.*;
@RequiredArgsConstructor @Getter
public enum CustomerExceptionProvider {
  INCORRECT_REGISTRATION_DETAILS("Incorrect registration details"),
  CUSTOMER_ID_ALREADY_EXIST("Customer id already exist"),
  CUSTOMER_NOT_FOUND("Customer not found"),
  
  ;
  private final String message;
}
