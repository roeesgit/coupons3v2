package com.JbSchool.coupons3.app.beans.security.entites.users;

import org.springframework.data.jpa.repository.*;
public interface CouponUserRepo extends JpaRepository<CouponUser,Integer> {
  
  CouponUser findByUsername(String username);
  
  boolean existsByUsername(String username);
  
  void deleteByUsername(String username);
  
  
  
  
  
}
