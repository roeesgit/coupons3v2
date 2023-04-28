package com.JbSchool.coupons3.app.beans.company.config;

import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface CompanyRepo extends JpaRepository< Company,Integer> {
  

  List <Company> findAll();
  
  boolean existsByEmailAndIdNot(String email, int id);
  
  Company findByEmail(String username);
  
  
  boolean existsByNameAndIdNot(String toString,int id);
  
  
  
}
