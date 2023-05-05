package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
public interface CouponService {
  
  CouponDto addCoupon(Coupon coupon);
  
  void updateCoupon(Coupon coupon, int id) throws CouponException;
  
  
  void deleteCoupon(int id) throws CouponException;
  
  
  
  
  List <Coupon> getCompanyCoupons();
  
  
  List <Coupon> getCustomerCoupons();
  
  
  List <Coupon> getCompanyCouponsByCategory(CategoryProvider categoryProvider);
  
  
  
  
  List <Coupon> getCustomerCouponsByCategory(CategoryProvider categoryProvider);
  
  List <Coupon> getCompanyCouponsByPrice(int price);
  
  
  List <Coupon> getCustomerCouponsByPrice(int price);
  
  
  List <Coupon> getValidCouponTOPurchaseForCustomer();
  
  
  @Transactional
  void buyCoupon(Coupon coupon, int customerId) throws CouponException;
  
  
  List<CouponDto> getAll();
  
  
}
