package com.JbSchool.coupons3.app.utils;

import lombok.*;

@Getter
@AllArgsConstructor

public enum ErrorMessageProvider {
  
  ID_NOT_FOUND("Id not found"),
  ID_ALREADY_EXIST("Id already exist"),
//todo כל השגיאות מכאן!!!!
  
  
  ;
  
  private final String message;
}
