package com.JbSchool.coupons3.app.beans.coupon.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
@Repository
public interface CouponRepo extends JpaRepository< Coupon,Integer> {
  
  
  int countByCompanyId(int id);
  
  
  boolean existsByTitleAndCompanyIdNot(String title, int id);
  
  
  boolean existsByCompanyId(int companyIdFromSCH);
  
  
}
