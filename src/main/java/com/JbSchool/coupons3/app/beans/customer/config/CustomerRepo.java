package com.JbSchool.coupons3.app.beans.customer.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
  
  boolean existsByEmail(String email);
  
  
  boolean existsByEmailAndIdNot(String toString, int scHid);
  
  
  Customer findByEmail(String username);
  
  
  
}
