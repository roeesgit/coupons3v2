package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final PersistenceCouponUser persistenceCouponUser;
  private final CouponExcValidator couponExcValidator;
  
  private final Mapper        mapper;
  
  private final CustomerRepo customerRepo;
  
  @Override
  public UserDto addCustomer(Customer customer) {
    Customer persistenceCustomer = this.persistenceCouponUser.addCustomer(customer);
    return this.mapper.customerToUserDto(persistenceCustomer);
  }
  @Override
  public void updateCustomer(Customer customer, int id) throws CouponException {
    Customer customerFromDb = this.couponExcValidator.getOptionalCustomer(id);
    
    this.mapper.customerToUserDto(
      this.persistenceCouponUser.updateCustomer(customer, customerFromDb));
  
  
  }
  @Override
  public void deleteCustomer(int id) throws CouponException {
    Customer customer = this.couponExcValidator.getOptionalCustomer(id);
    this.persistenceCouponUser.deleteCustomer(customer.getEmail(),id);
  }
  @Override
  public List <UserDto> getAllCustomers() {
    return this.mapper.customerListToUserDtoList(this.customerRepo.findAll());
  }
  
  @Override
  public UserDto getCustomer(int id) throws CouponException {
    Customer customer = this.couponExcValidator.getOptionalCustomer(id);
    return this.mapper.customerToUserDto(customer);
  }
  
  @Override
  public UserDto getLoggedCustomer() throws CouponException {
    Customer customer = this.couponExcValidator.getOptionalCustomer(this.mapper.userIdFromSCH());
    return this.mapper.customerToUserDto(customer);
  }
}
