package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.config.*;
import com.JbSchool.coupons3.app.utils.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

/**
 The CouponService interface provides methods for managing coupons.
 */
public interface CouponService {
  
  /**
   Adds a new coupon.
   
   @param coupon the coupon to add
   @return the added coupon DTO
   @throws CouponException if an error occurs while adding the coupon
   */
  CouponDto addCoupon(Coupon coupon) throws CouponException;
  
  /**
   Updates an existing coupon.
   
   @param coupon the updated coupon
   @param id the ID of the coupon to update
   @throws CouponException if an error occurs while updating the coupon
   */
  void updateCoupon(Coupon coupon, int id) throws CouponException;
  
  /**
   Deletes a coupon by ID.
   
   @param id the ID of the coupon to delete
   @throws CouponException if an error occurs while deleting the coupon
   */
  void deleteCoupon(int id) throws CouponException;
  
  /**
   Retrieves a list of coupons owned by the company.
   
   @return a list of company coupons
   */
  List <CouponDto> getCompanyCoupons();
  
  /**
   Retrieves a list of coupons owned by the customer.
   
   @return a list of customer coupons
   */
  List <CouponDto> getCustomerCoupons();
  
  /**
   Retrieves a list of coupons owned by the company and matching the specified category.
   
   @param categoryProvider the category of the coupons
   @return a list of company coupons by category
   */
  List <CouponDto> getCompanyCouponsByCategory(CategoryProvider categoryProvider);
  
  /**
   Retrieves a list of coupons owned by the customer and matching the specified category.
   
   @param categoryProvider the category of the coupons
   @return a list of customer coupons by category
   */
  List <CouponDto> getCustomerCouponsByCategory(CategoryProvider categoryProvider);
  
  /**
   Retrieves a list of coupons owned by the company and with a price less than or equal to the specified price.
   
   @param price the maximum price of the coupons
   @return a list of company coupons by price
   */
  List <CouponDto> getCompanyCouponsByPrice(int price);
  
  /**
   Retrieves a list of coupons owned by the customer and with a price less than or equal to the specified price.
   
   @param price the maximum price of the coupons
   @return a list of customer coupons by price
   */
  List <CouponDto> getCustomerCouponsByPrice(int price);
  
  /**
   Retrieves a list of valid coupons available for purchase by the customer.
   
   @return a list of valid coupons for purchase
   */
  List <CouponDto> getValidCouponTOPurchaseForCustomer();
  
  /**
   Buys a coupon for the customer.
   
   @param coupon the coupon to buy
   @param customerId the ID of the customer
   @throws CouponException if an error occurs while buying the coupon
   */
  @Transactional
  void buyCoupon(Coupon coupon, int customerId) throws CouponException;
  
  /**
   Retrieves a list of all coupons.
   
   @return a list of all coupons
   */
  List <CouponDto> getAll();
  
  /**
   Retrieves a coupon by ID.
   
   @param id the ID of the coupon
   @return the coupon DTO
   @throws CouponException if the coupon is not found
   */
  CouponDto getCouponById(int id) throws CouponException;
  
  
}
