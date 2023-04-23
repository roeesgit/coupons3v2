package com.JbSchool.coupons3.app.beans.coupon.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
@Repository
public interface CouponRepo extends JpaRepository< Coupon,Integer> {
  
  int countByCompany_Id(int id);
  
  
  boolean existsByTitleAndCompany_IdNot(String title, int id);
  
  
  
}
