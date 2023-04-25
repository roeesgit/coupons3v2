package com.JbSchool.coupons3.security.entites.auth;

import lombok.*;
import org.springframework.security.core.*;
@ToString
public enum CouponAuthorization {
  ROLE_ADMIN,
  ROLE_CUSTOMER,
  ROLE_COMPANY,
  UPDATE,
  GET,
  POST,
  DELETE,
  SPECIAL_PERMISSION,
  
  
  ;
  
  
  

}
