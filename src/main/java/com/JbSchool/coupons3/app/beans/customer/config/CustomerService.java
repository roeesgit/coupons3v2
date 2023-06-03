package com.JbSchool.coupons3.app.beans.customer.config;
import com.JbSchool.coupons3.app.utils.CouponException;
import java.util.List;

/**
 * Service interface for managing customers.
 */
public interface CustomerService {
  
  /**
   * Adds a new customer.
   *
   * @param customer the customer to add
   * @return the added customer DTO
   * @throws CouponException if an error occurs during the operation
   */
  CustomerDto addCustomer(Customer customer) throws CouponException;
  
  /**
   * Updates an existing customer.
   *
   * @param customer the customer to update
   * @param id the ID of the customer to update
   * @return the updated customer DTO
   * @throws CouponException if an error occurs during the operation
   */
  CustomerDto updateCustomer(Customer customer, int id) throws CouponException;
  
  /**
   * Retrieves a customer by ID.
   *
   * @param id the ID of the customer to retrieve
   * @return the customer DTO with the specified ID
   * @throws CouponException if an error occurs during the operation or if the customer is not found
   */
  CustomerDto getCustomer(int id) throws CouponException;
  
  /**
   * Deletes a customer by ID.
   *
   * @param id the ID of the customer to delete
   * @throws CouponException if an error occurs during the operation or if the customer is not found
   */
  void deleteCustomer(int id) throws CouponException;
  
  /**
   * Retrieves a list of all customers.
   *
   * @return a list of all customer DTOs
   */
  List<CustomerDto> getAllCustomers();
  
  /**
   * Retrieves the currently logged-in customer.
   *
   * @return the DTO of the logged-in customer
   * @throws CouponException if no customer is currently logged in
   */
  CustomerDto getLoggedCustomer() throws CouponException;
  
  /**
   * Finds a customer by email.
   *
   * @param email the email to search for
   * @return the customer DTO with the specified email, or null if not found
   */
  CustomerDto findByEmail(String email);
}