package com.JbSchool.coupons3.security.entites.auth;

import lombok.*;
import org.springframework.security.core.*;
/**
 * Enum class representing the different types of authorization roles and permissions.
 */
@ToString
public enum CouponAuthorization {
  /*Authorization Role*/
  ROLE_ADMIN,
  /*Authorization Role*/
  ROLE_COMPANY,
  /*Authorization Role*/
  ROLE_CUSTOMER,
  /*Authorization Action*/
  UPDATE,
  /*Authorization Action*/
  GET,
  /*Authorization Action*/
  POST,
  /*Authorization Action*/
  DELETE,
  /*Authorization Action*/
  SPECIAL_PERMISSION,
  /*Authorization Action*/
  
  
  ;
  
  
  

}
