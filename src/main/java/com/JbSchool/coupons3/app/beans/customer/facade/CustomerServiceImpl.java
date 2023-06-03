package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final PersistenceCouponUser persistenceCouponUser;
  private final CustomerValidator     customerValidator;
  
  private final Mapper        mapper;
  
  private final CustomerRepo customerRepo;
  
  @Override
  public CustomerDto addCustomer(Customer customer) throws CouponException {
    this.customerValidator.addCustomer(customer);
    Customer persistenceCustomer = this.persistenceCouponUser.addCustomer(customer);
    return this.mapper.customerToCustomerDto(persistenceCustomer);
  }
  @Override
  public CustomerDto updateCustomer(Customer customer, int id) throws CouponException {
    this.customerValidator.updateCustomer(customer, id);
    Customer customerFromDb = this.customerValidator.getOptionalCustomer(id);
    
   return this.mapper.customerToCustomerDto(
      this.persistenceCouponUser.updateCustomer(customer, customerFromDb));
  
  
  }
  @Override
  public void deleteCustomer(int id) throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(id);
    this.persistenceCouponUser.deleteCustomer(customer.getEmail(),id);
  }
  @Override
  public List <CustomerDto> getAllCustomers() {
    return this.mapper.customerListToCustomerDtoList(this.customerRepo.findAll());
  }
  
  @Override
  public CustomerDto getCustomer(int id) throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(id);
    return this.mapper.customerToCustomerDto(customer);
  }
  
  @Override
  public CustomerDto getLoggedCustomer() throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(this.mapper.userIdFromSCH());
    return this.mapper.customerToCustomerDto(customer);
  }
  
  
  @Override
  public CustomerDto findByEmail(String email) {
    return this.mapper.customerToCustomerDto(this.customerRepo.findByEmail(email));
  }
  
  
}
