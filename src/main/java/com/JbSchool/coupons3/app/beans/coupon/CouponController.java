package com.JbSchool.coupons3.app.beans.coupon;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.dto.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
  
  private final CouponService couponService;
  
  
  @PostMapping
  public CouponDto post(@RequestBody @Valid Coupon coupon) {
    return this.couponService.addCoupon(coupon);
  }
  @PutMapping
  public String put() {
    return "COUPON PUT";
  }
  @GetMapping
  public List<Coupon> get() {
    return this.couponService.getAllCoupons();
  }
  @DeleteMapping
  public String delete() {
    return "COUPON DELETE";
  }
  
}
