package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.beans.company.facade.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;

import java.util.*;
public interface CompanyService {
  
  CompanyRespondDto addCompany(Company company) throws CouponException;
  
  
  TokenResponseDTO updateCompany(Company company, int id) throws CouponException;
  
  
  CompanyRespondDto getSingleCompany(int id) throws CouponException;
  
  
  void deleteCompany(int id) throws CouponException;
  
  
  List <Company> getAllCompanies();
  
  
}
