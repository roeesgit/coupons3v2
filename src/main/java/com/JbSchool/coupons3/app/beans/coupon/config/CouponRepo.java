package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;
@Repository
public interface CouponRepo extends JpaRepository <Coupon, Integer> {
  
  boolean existsByTitleAndCompanyId(String title, int id);
  
  
  boolean existsByIdAndCompanyId(int couponId, int companyId);
  
  List <Coupon> findByCompanyIdAndCategory(int companyId, CategoryProvider category);
  
  List <Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, int price);
  
  
  
  List <Coupon> findByCompanyId(int companyId);
  
  
  @Query(nativeQuery = true, value = """
    select * from coupons
    where
    id in(select coupon_id from purchases where customer_id = ? )\s
    group by id;"""
  )
  List <Coupon> getCustomerCoupons(int userId);
  @Query(nativeQuery = true, value = """
    select * from coupons\s
    where
    id in(select coupon_id from purchases where customer_id = ? ) \s
    and category = ?\s
    group by id;"""
  )
  List <Coupon> getCustomerCouponsByCategory(int userId,CategoryProvider category);
  
  @Query(nativeQuery = true, value = """
    select * from coupons
    where
    id not in(select coupon_id from purchases where customer_id = ? )\s
    and
    amount > 0\s
    and end_date >= now()\s
    group by id;"""
  )
  List <Coupon> getValidCouponTOPurchaseForCustomer(int userId);
  
  
  int deleteByCompanyId(int id);
  
  
}
