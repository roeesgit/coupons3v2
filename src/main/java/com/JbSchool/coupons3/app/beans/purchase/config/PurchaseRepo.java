package com.JbSchool.coupons3.app.beans.purchase.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
  
  void deleteByCouponId(int couponId);
  
  
  
  int deleteByCustomerId(int customerId);
  
  
  @Query(nativeQuery = true,value =
    "delete from purchases where coupon_id in(select id from coupons where company_id = ?)"
  )
  int deleteCompanyCouponsPurchases(int id);
  
  
}
