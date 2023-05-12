package com.JbSchool.coupons3.app.beans.company.config;

import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface CompanyRepo extends JpaRepository< Company,Integer> {
  

  List <Company> findAll();
  
  boolean existsByEmailAndIdNot(String email, int id);
  
  
//  @Query(nativeQuery = true,value = "select (count(c.id) > 0) from Companies c where c.email = ?1 and c.email != ?1")
//  int existsByEmailAndNotEmail(String email);
  
  Company findByEmail(String username);
  
  
  boolean existsByNameAndIdNot(String toString,int id);
  
//  @Query(nativeQuery = true,value = "select (count(c.id) > 0) from Companies c where c.name = ?1 and c.name != ?1")
//  int existsByNameAndNameNot(String toString);
  
  
}
