package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
  
  private final CouponServiceImpl couponServiceImpl;
  private final Mapper mapper;
  
  
  
  @PostMapping
  public CouponDto addCoupon(@RequestBody @Valid Coupon coupon) {
    return this.couponServiceImpl.addCoupon(coupon);
  }
  @PutMapping("/{id}")
  public void updateCoupon(@RequestBody @Valid Coupon coupon , @PathVariable int id) throws CouponException {
    this.couponServiceImpl.updateCoupon(coupon,id);
  }
  
  
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) throws CouponException {
    this.couponServiceImpl.deleteCoupon(id);
  }
  
    @GetMapping("/company")
  public List <Coupon> getCompanyCoupons() {
    return this.couponServiceImpl.getCompanyCoupons();
  }
  
  @GetMapping("/company/{category}")
  public List <Coupon> getCompanyCouponsByCategory(@PathVariable String category) {
    return this.couponServiceImpl.getCompanyCouponsByCategory(CategoryProvider.valueOf(category));
  }
  
  @GetMapping("/company/{price}")
  public List <Coupon> getCompanyCouponsByPrice(@PathVariable int price) {
    return this.couponServiceImpl.getCompanyCouponsByPrice(price);
  }
  
  
  @GetMapping("/customer")
  public List <Coupon> getCustomerCoupons() {
    return this.couponServiceImpl.getCustomerCoupons();
  }
  
  @GetMapping("/customer/{category}")
  public List <Coupon> getCustomerCouponsByCategory(@PathVariable String category) {
    return this.couponServiceImpl.getCustomerCouponsByCategory(CategoryProvider.valueOf(category));
  }
  
  @GetMapping("/customer/{price}")
  public List <Coupon> getCustomerCouponsByPrice(@PathVariable int price) {
    return this.couponServiceImpl.getCustomerCouponsByPrice(price);
  }
   @GetMapping("/customer/validToBuy")
  public List <Coupon> getValidCouponTOPurchaseForCustomer() {
    return this.couponServiceImpl.getValidCouponTOPurchaseForCustomer();
  }
  
   @GetMapping("/customer/buyCoupon")
  public void buyCoupon(@RequestBody Coupon coupon) throws CouponException {
    this.couponServiceImpl.buyCoupon(coupon ,this.mapper.userIdFromSCH());
  }
  
  
  
  
  }
