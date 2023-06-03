package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 
 Controller class for managing customers.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
  
  private final CustomerServiceImpl customerService;
  
  /**
   
   Adds a new customer.
   @param customer the customer to add
   @return the added customer DTO
   @throws CouponException if an error occurs during the operation
   */
  @PostMapping
  public CustomerDto addCustomer(@RequestBody @Valid Customer customer) throws CouponException {
    return this.customerService.addCustomer(customer);
  }
  
  /**
   
   Updates an existing customer.
   @param customer the customer to update
   @param customerId the ID of the customer to update
   @return the updated customer DTO
   @throws CouponException if an error occurs during the operation
   */
  @PutMapping("/{customerId}")
  public CustomerDto updateCustomer(@RequestBody @Valid Customer customer, @PathVariable int customerId) throws CouponException {
    return customerService.updateCustomer(customer, customerId);
  }
  
  /**
   
   Deletes a customer.
   @param id the ID of the customer to delete
   @throws CouponException if an error occurs during the operation
   */
  @DeleteMapping("/{id}")
  public void deleteCustomer(@PathVariable int id) throws CouponException {
    this.customerService.deleteCustomer(id);
  }
  
  /**
   
   Retrieves a list of all customers.
   @return a list of all customer DTOs
   */
  @GetMapping
  public List <CustomerDto> getAllCustomers() {
    return this.customerService.getAllCustomers();
  }
  
  /**
   
   Retrieves a single customer by ID.
   @param id the ID of the customer to retrieve
   @return the customer DTO with the specified ID
   @throws CouponException if an error occurs during the operation or if the customer is not found
   */
  @GetMapping("/{id}")
  public CustomerDto getSingleCustomer(@PathVariable int id) throws CouponException {
    return this.customerService.getCustomer(id);
  }
  
  /**
   
   Retrieves a single customer by email.
   @param email the email of the customer to retrieve
   @return the customer DTO with the specified email
   */
  @GetMapping("/{email}")
  public CustomerDto getSingleCustomer(@PathVariable String email) {
    return this.customerService.findByEmail(email);
  }
  
  /**
   
   Retrieves the currently logged-in customer.
   @return the DTO of the logged-in customer
   @throws CouponException if no customer is currently logged in
   */
  @GetMapping("/loggedCustomer")
  public CustomerDto getLoggedCustomer() throws CouponException {
    return this.customerService.getLoggedCustomer();
  }
  
  
}
