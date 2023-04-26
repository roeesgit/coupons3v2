package com.JbSchool.coupons3.app.beans.company.config;

import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface CompanyRepo extends JpaRepository< Company,Integer> {
  

  List <Company> findAll();
  boolean existsByName(String name);
  boolean existsByEmail(String email);
  
  boolean existsByEmailAndIdNot(String email, int id);
  
  
  Company findByEmail(String username);
  
  
}
