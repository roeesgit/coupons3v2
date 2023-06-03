package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.utils.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
public interface CouponService {
  
  CouponDto addCoupon(Coupon coupon) throws CouponException;
  
  void updateCoupon(Coupon coupon, int id) throws CouponException;
  
  
  void deleteCoupon(int id) throws CouponException;
  
  
  
  
  List <CouponDto> getCompanyCoupons();
  
  
  List <CouponDto> getCustomerCoupons();
  
  
  List <CouponDto> getCompanyCouponsByCategory(CategoryProvider categoryProvider);
  
  
  
  
  List <CouponDto> getCustomerCouponsByCategory(CategoryProvider categoryProvider);
  
  List <CouponDto> getCompanyCouponsByPrice(int price);
  
  
  List <CouponDto> getCustomerCouponsByPrice(int price);
  
  
  List <CouponDto> getValidCouponTOPurchaseForCustomer();
  
  
  @Transactional
  void buyCoupon(Coupon coupon, int customerId) throws CouponException;
  
  
  List<CouponDto> getAll();
  
  
  CouponDto getCouponById(int id) throws CouponException;
  
  
}
