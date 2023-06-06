/**
 
 AuthExceptionProvider is an enumeration of authentication exception providers.
 It contains predefined messages for common authentication exceptions.
 */
package com.JbSchool.coupons3.security.exception;


import lombok.*;
@Getter @AllArgsConstructor
public enum AuthExceptionProvider {
  /*Exception provider*/
  EMAIL_ALREADY_EXIST("Email already exist"),
  /*Exception provider*/
  USERNAME_NOT_FOUND("username (email) not found"),
  /*Exception provider*/
  ID_ALREADY_EXIST("Id already exist"),

;
  
  
  /*Exception message*/
  private final String message;

}
