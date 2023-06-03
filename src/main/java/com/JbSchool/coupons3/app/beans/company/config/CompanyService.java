package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.utils.*;

import java.util.*;
public interface CompanyService {
  
  CompanyDto addCompany(Company company) throws CouponException;
  
  
  void updateCompany(Company company,int companyId) throws CouponException;
  
  
  CompanyDto getSingleCompany(int id) throws CouponException;
  
  
  void deleteCompany(int id) throws CouponException;
  
  
  List <CompanyDto> getAllCompanies();
  
  
  CompanyDto getLoggedCompany() throws CouponException;
  
  
  CompanyDto findByEmail(String email);
  
  
}
