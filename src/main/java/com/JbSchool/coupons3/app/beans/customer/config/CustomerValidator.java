package com.JbSchool.coupons3.app.beans.customer.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.config.*;
import lombok.*;
import org.springframework.stereotype.*;
@Component
@RequiredArgsConstructor
public class CustomerValidator {
  
  private final CustomerRepo        customerRepo;
  private final PurchaseRepo        purchaseRepo;
  private final CouponUserValidator couponUserValidator;
  
  
  public void addCustomer(Customer customer) throws CouponException {
    if (customer.getId() != 0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
    this.couponUserValidator.addEmail(customer.getEmail());
  }
  
  
  public void updateCustomer(Customer customer, int id) throws CouponException {
    Customer customerFromDb = getOptionalCustomer(id);
    this.couponUserValidator.updateEmail(customerFromDb.getEmail(), customer.getEmail());
  }
  
  
  public Customer getOptionalCustomer(int id) throws CouponException {
    return this.customerRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  
}
