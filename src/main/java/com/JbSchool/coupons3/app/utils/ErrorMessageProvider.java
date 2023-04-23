package com.JbSchool.coupons3.app.utils;

import lombok.*;

@Getter
@AllArgsConstructor

public enum ErrorMessageProvider {
  
  ID_NOT_FOUND("Id not found"),
  ID_ALREADY_EXIST("Id already exist"),
  NAME_ALREADY_EXIST("Name already exist"),
  EMAIl_ALREADY_EXIST("Email already exist"),
  Null("Null values are not allowed"),
  
  
  ;
  
  private final String message;
}
