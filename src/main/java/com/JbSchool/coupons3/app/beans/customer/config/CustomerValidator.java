package com.JbSchool.coupons3.app.beans.customer.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.config.*;
import lombok.*;
import org.springframework.stereotype.*;

/**
 
 Validator class for validating customer operations.
 */
@Component
@RequiredArgsConstructor
public class CustomerValidator {
  
  private final CustomerRepo        customerRepo;
  private final PurchaseRepo        purchaseRepo;
  private final CouponUserValidator couponUserValidator;
  
  /**
   
   Validates the addition of a customer.
   @param customer the customer to add
   @throws CouponException if validation fails
   */
  public void addCustomer(Customer customer) throws CouponException {
    if (customer.getId() != 0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
    this.couponUserValidator.addEmail(customer.getEmail());
  }
  
  /**
   
   Validates the update of a customer.
   @param customer the updated customer
   @param id the ID of the customer to update
   @throws CouponException if validation fails
   */
  public void updateCustomer(Customer customer, int id) throws CouponException {
    Customer customerFromDb = getOptionalCustomer(id);
    this.couponUserValidator.updateEmail(customerFromDb.getEmail(), customer.getEmail());
  }
  /**
   
   Retrieves an optional customer by ID.
   @param id the ID of the customer to retrieve
   @return the customer entity with the specified ID
   @throws CouponException if the customer is not found
   */
  
  public Customer getOptionalCustomer(int id) throws CouponException {
    return this.customerRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  
}
