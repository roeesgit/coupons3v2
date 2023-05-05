package com.JbSchool.coupons3.app.beans.security.entites.auth;

import lombok.*;
import org.springframework.security.core.*;
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
