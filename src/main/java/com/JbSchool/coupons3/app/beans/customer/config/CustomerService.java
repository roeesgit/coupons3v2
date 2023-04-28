package com.JbSchool.coupons3.app.beans.customer.config;

import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;

import java.util.*;
public interface CustomerService {
  
  UserDto addCustomer(Customer customer);
  
  void updateCustomer(Customer customer, int id) throws CouponException;
  
  UserDto getCustomer(int id) throws CouponException;
  
  void deleteCustomer(int id) throws CouponException;
  
  
  List <UserDto> getAllCustomers();
  
  UserDto getLoggedCustomer() throws CouponException;
  
  
}
