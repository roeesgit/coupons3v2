package com.JbSchool.coupons3.app.beans.customer.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
  
  boolean existsByEmail(String email);
  
  
  boolean existsByEmailAndIdNot(String toString, int scHid);
  
  
  Customer findByEmail(String username);
  
  @Query(nativeQuery = true,value = "select (count(c.id) > 0) from customers c where c.email = ?1 and c.email != ?1")
  int existsByEmailAndNotEmail(String toString, int scHid);
  
  
}
