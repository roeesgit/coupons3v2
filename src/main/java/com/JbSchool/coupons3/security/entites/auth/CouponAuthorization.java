package com.JbSchool.coupons3.security.entites.auth;

import lombok.*;
import org.springframework.security.core.*;
/**
 * Enum class representing the different types of authorization roles and permissions.
 */
@ToString
public enum CouponAuthorization {
  ROLE_ADMIN,
  ROLE_COMPANY,
  ROLE_CUSTOMER,
  UPDATE,
  GET,
  POST,
  DELETE,
  SPECIAL_PERMISSION,
  
  
  ;
  
  
  

}
