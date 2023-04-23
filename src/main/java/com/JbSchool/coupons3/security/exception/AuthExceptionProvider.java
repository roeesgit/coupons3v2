package com.JbSchool.coupons3.security.exception;


import lombok.*;
@Getter @AllArgsConstructor
public enum AuthExceptionProvider {
  EMAIL_ALREADY_EXIST("Email already exist"),
  USERNAME_NOT_FOUND("username (email) not found"),
  ID_ALREADY_EXIST("Id already exist"),

;
  private final String message;

}
