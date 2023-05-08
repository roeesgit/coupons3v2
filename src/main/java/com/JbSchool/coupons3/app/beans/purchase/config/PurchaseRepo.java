package com.JbSchool.coupons3.app.beans.purchase.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
  
  void deleteByCouponId(int couponId);
  
  
  
  int deleteByCustomerId(int customerId);
  
  
  
  Purchase findByCustomerIdAndCouponId(int customerId, int couponId);
//
  
  
}
