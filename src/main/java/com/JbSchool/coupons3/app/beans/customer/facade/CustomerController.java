package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController @RequiredArgsConstructor
  @RequestMapping("/api/v1/customers")
public class CustomerController {
  
  private final CustomerServiceImpl customerService;
  
  @PostMapping
  public UserDto addCustomer(@RequestBody @Valid Customer Customer){
    return this.customerService.addCustomer(Customer);
  }
  
  @PutMapping("/{customerId}")
  public void updateCustomer(@RequestBody @Valid Customer customer, @PathVariable int customerId) throws CouponException {
    customerService.updateCustomer(customer,customerId);
  }
  
  @DeleteMapping("/{id}")
  public void deleteCustomer( @PathVariable int id) throws CouponException {
    this.customerService.deleteCustomer(id);
  }
  
  @GetMapping
  public List <UserDto> getAllCustomers(){
    return this.customerService.getAllCustomers();
  }
  
  
  @GetMapping("/{id}")
  public UserDto getSingleCustomer(@PathVariable int id) throws CouponException {
    return this.customerService.getCustomer(id);
  }
  @GetMapping("/loggedCustomer")
  public UserDto getLoggedCustomer() throws CouponException {
    return this.customerService.getLoggedCustomer();
  }


}
