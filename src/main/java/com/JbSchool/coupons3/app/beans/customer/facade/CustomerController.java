package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
  
  private final CustomerServiceImpl customerService;
  
  
  @PostMapping
  public CustomerDto addCustomer(@RequestBody @Valid Customer customer) throws CouponException {
    return this.customerService.addCustomer(customer);
  }
  
  
  @PutMapping("/{customerId}")
  public CustomerDto updateCustomer(@RequestBody @Valid Customer customer, @PathVariable int customerId) throws CouponException {
    return customerService.updateCustomer(customer, customerId);
  }
  
  
  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable int id) throws CouponException {
    this.customerService.deleteCustomer(id);
  }
  
  
  @GetMapping
  public List <CustomerDto> getAllCustomers() {
    return this.customerService.getAllCustomers();
  }
  
  
  @GetMapping("/{id}")
  public CustomerDto getSingleCustomer(@PathVariable int id) throws CouponException {
    return this.customerService.getCustomer(id);
  }
  
  
  @GetMapping("/{email}")
  public CustomerDto getSingleCustomer(@PathVariable String email) {
    return this.customerService.findByEmail(email);
  }
  
  
  @GetMapping("/loggedCustomer")
  public CustomerDto getLoggedCustomer() throws CouponException {
    return this.customerService.getLoggedCustomer();
  }
  
  
}
