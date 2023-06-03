package com.JbSchool.coupons3.app.beans.customer.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
/**
 
 Repository interface for performing CRUD operations on the Customer entity.
 */

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
  
  /**
   
   Checks if a customer with the given email exists.
   @param email the email to check
   @return true if a customer with the email exists, false otherwise
   */
  boolean existsByEmail(String email);
  /**
   
   Checks if a customer with the given email exists, excluding the customer with the specified ID.
   @param email the email to check
   @param id the ID of the customer to exclude
   @return true if a customer with the email exists and its ID is not equal to the specified ID, false otherwise
   */
  boolean existsByEmailAndIdNot(String email, int id);
  /**
   
   Retrieves a customer with the given email.
   @param email the email to search for
   @return the customer with the specified email, or null if not found
   */
  Customer findByEmail(String email);
  /**
   
   Checks if a customer with the given email exists, excluding the customer with the specified email and ID.
   @param email the email to check
   @param id the ID of the customer to exclude
   @return true if a customer with the email exists and its ID is not equal to the specified ID, false otherwise
   */
  @Query(nativeQuery = true, value = "SELECT (COUNT(c.id) > 0) FROM customers c WHERE c.email = ?1 AND c.email != ?1")
  int existsByEmailAndNotEmail(String email, int id);
}