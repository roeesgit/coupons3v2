package com.JbSchool.coupons3.app.beans.customer.facade;

import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 
 Implementation of the CustomerService interface.
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final PersistenceCouponUser persistenceCouponUser;
  private final CustomerValidator     customerValidator;
  
  private final Mapper        mapper;
  
  private final CustomerRepo customerRepo;
  /**
   * Adds a customer to the system.
   *
   * @param customer the customer to be added
   * @return the added customer as a CustomerDto object
   * @throws CouponException if there is an error adding the customer
   */
  @Override
  public CustomerDto addCustomer(Customer customer) throws CouponException {
    this.customerValidator.addCustomer(customer);
    Customer persistenceCustomer = this.persistenceCouponUser.addCustomer(customer);
    return this.mapper.customerToCustomerDto(persistenceCustomer);
  }
  
  /**
   * Updates an existing customer in the system.
   *
   * @param customer   the updated customer information
   * @param id         the ID of the customer to be updated
   * @return the updated customer as a CustomerDto object
   * @throws CouponException if there is an error updating the customer
   */
  @Override
  public CustomerDto updateCustomer(Customer customer, int id) throws CouponException {
    this.customerValidator.updateCustomer(customer, id);
    Customer customerFromDb = this.customerValidator.getOptionalCustomer(id);
    
   return this.mapper.customerToCustomerDto(
      this.persistenceCouponUser.updateCustomer(customer, customerFromDb));
  
  
  }
  
  /**
   * Deletes a customer from the system.
   *
   * @param id the ID of the customer to be deleted
   * @throws CouponException if there is an error deleting the customer
   */
  @Override
  public void deleteCustomer(int id) throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(id);
    this.persistenceCouponUser.deleteCustomer(customer.getEmail(),id);
  }
  
  /**
   * Retrieves a list of all customers in the system.
   *
   * @return a list of CustomerDto objects representing all customers
   */
  @Override
  public List <CustomerDto> getAllCustomers() {
    return this.mapper.customerListToCustomerDtoList(this.customerRepo.findAll());
  }
  /**
   * Retrieves a customer by ID.
   *
   * @param id the ID of the customer to retrieve
   * @return the retrieved customer as a CustomerDto object
   * @throws CouponException if the customer with the specified ID is not found
   */
  @Override
  public CustomerDto getCustomer(int id) throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(id);
    return this.mapper.customerToCustomerDto(customer);
  }
  /**
   * Retrieves the logged-in customer.
   *
   * @return the logged-in customer as a CustomerDto object
   * @throws CouponException if there is an error retrieving the logged-in customer
   */
  @Override
  public CustomerDto getLoggedCustomer() throws CouponException {
    Customer customer = this.customerValidator.getOptionalCustomer(this.mapper.userIdFromSCH());
    return this.mapper.customerToCustomerDto(customer);
  }
  
  /**
   * Finds a customer by email.
   *
   * @param email the email of the customer to find
   * @return the found customer as a CustomerDto object
   */
  @Override
  public CustomerDto findByEmail(String email) {
    return this.mapper.customerToCustomerDto(this.customerRepo.findByEmail(email));
  }
  
  
}
