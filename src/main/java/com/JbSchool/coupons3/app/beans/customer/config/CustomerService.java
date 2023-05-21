package com.JbSchool.coupons3.app.beans.customer.config;

import com.JbSchool.coupons3.app.utils.*;

import java.util.*;
public interface CustomerService {
  
  CustomerDto addCustomer(Customer customer) throws CouponException;
  
  CustomerDto updateCustomer(Customer customer, int id) throws CouponException;
  
  CustomerDto getCustomer(int id) throws CouponException;
  
  void deleteCustomer(int id) throws CouponException;
  
  
  List <CustomerDto> getAllCustomers();
  
  CustomerDto getLoggedCustomer() throws CouponException;
  
  
}
