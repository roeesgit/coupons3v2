package com.JbSchool.coupons3.app.beans.company.config;

import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface CompanyRepo extends JpaRepository< Company,Integer> {
  
   // TODO: 11/22/2022 test entity graph, try to use for repo class!
  @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"coupons"})
  public List <Company> findAll();
  boolean existsByName(String name);
  boolean existsByEmail(String email);
  
  boolean existsByEmailAndIdNot(String email, int id);

  
}
