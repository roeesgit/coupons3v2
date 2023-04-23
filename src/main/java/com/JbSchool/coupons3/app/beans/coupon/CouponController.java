package com.JbSchool.coupons3.app.beans.coupon;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/coupons")
public class CouponController {
  
  @PostMapping
  public String post() {
    return "COUPON POST";
  }
  @PutMapping
  public String put() {
    return "COUPON PUT";
  }
  @GetMapping
  public String get() {
    return "COUPON GET";
  }
  @DeleteMapping
  public String delete() {
    return "COUPON DELETE";
  }
  
}
