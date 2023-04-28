package com.JbSchool.coupons3.app.beans.coupon.config;

import lombok.*;
@RequiredArgsConstructor
@Getter

public enum CouponExceptionProvider {
  COUPON_ID_DOES_NOT_EXIST("Coupon id doesn't exist"),
  COUPON_NOT_OWNED_BY_COMPANY("Coupon is not owned by this company"),
  COUPON_ALREADY_OWNED_BY_CUSTOMER("Coupon already owned by this customer"),
  COUPON_EXPIRED("Coupon expired"),
  OUT_OF_STOCK("Coupon out of stock"),
  
  ;
  
  private final String message;
  
}
