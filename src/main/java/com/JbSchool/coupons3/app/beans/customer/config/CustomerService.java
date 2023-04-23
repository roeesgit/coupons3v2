package com.JbSchool.coupons3.app.beans.customer.config;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service
@RequiredArgsConstructor
public class CustomerService {
  
  private final CustomerRepo customerRepo;
  
  
  public Customer addCustomer(Customer customer) {
   return this.customerRepo.save(customer);
  }
  public void updateCustomer(Customer customer,int id) {
   this.customerRepo.save(customer);
  }
  public Customer getCustomer(int id) {
   return this.customerRepo.findById(id).orElseThrow();
  }
  public void removeCustomer(int id) {
   this.customerRepo.deleteById(id);
  }
}
