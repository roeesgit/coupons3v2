package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
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
  public CouponDto addCoupon(@RequestBody @Valid Coupon coupon) throws CouponException {
    return this.couponServiceImpl.addCoupon(coupon);
  }
  @PutMapping("/{id}")
  public void updateCoupon(@RequestBody @Valid Coupon coupon , @PathVariable int id) throws CouponException {
    this.couponServiceImpl.updateCoupon(coupon,id);
  }
  
  @GetMapping("/customer/{id}")
  public CouponDto getCouponById(@PathVariable int id) throws CouponException {
    return this.couponServiceImpl.getCouponById(id);
  }
  @GetMapping("/all")
  public List <CouponDto> getAll() {
    return this.couponServiceImpl.getAll();
  }
  
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) throws CouponException {
    this.couponServiceImpl.deleteCoupon(id);
  }
  
    @GetMapping("/company")
  public List <CouponDto> getCompanyCoupons() {
    return this.couponServiceImpl.getCompanyCoupons();
  }
  
//  @GetMapping("/company/byCategory/{category}")
//  public List <CouponDto> getCompanyCouponsByCategory(@PathVariable String category) {
//    return this.couponServiceImpl.getCompanyCouponsByCategory(CategoryProvider.valueOf(category.toUpperCase()));
//  }
  
//  @GetMapping("/company/byPrice/{price}")
//  public List <CouponDto> getCompanyCouponsByPrice(@PathVariable int price) {
//    return this.couponServiceImpl.getCompanyCouponsByPrice(price);
//  }
//
  
  @GetMapping("/customer")
  public List <CouponDto> getCustomerCoupons() {
    return this.couponServiceImpl.getCustomerCoupons();
  }
  
//  @GetMapping("/customer/{category}")
//  public List <CouponDto> getCustomerCouponsByCategory(@PathVariable String category) {
//    return this.couponServiceImpl.getCustomerCouponsByCategory(CategoryProvider.valueOf(category.toUpperCase()));
//  }
////
//  @GetMapping("/customer/byPrice/{price}")
//  public List <CouponDto> getCustomerCouponsByPrice(@PathVariable int price) {
//    return this.couponServiceImpl.getCustomerCouponsByPrice(price);
//  }
   @GetMapping("/customer/validToBuy")
  public List <CouponDto> getValidCouponTOPurchaseForCustomer() {
    return this.couponServiceImpl.getValidCouponTOPurchaseForCustomer();
  }
  
   @PostMapping("/customer/buyCoupon")
  public void buyCoupon(@RequestBody Coupon coupon) throws CouponException {
    this.couponServiceImpl.buyCoupon(coupon ,this.mapper.userIdFromSCH());
  }
  
  
  
  
  }
