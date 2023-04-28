package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;

import java.util.*;
public interface CompanyService {
  
  UserDto addCompany(Company company) throws CouponException;
  
  
  void updateCompany(Company company,int companyId) throws CouponException;
  
  
  UserDto getSingleCompany(int id) throws CouponException;
  
  
  void deleteCompany(int id) throws CouponException;
  
  
  List <UserDto> getAllCompanies();
  
  
  UserDto getLoggedCompany() throws CouponException;
  
  
}
