package com.JbSchool.coupons3.app.beans.purchase.config;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

/**
 * Repository interface for managing purchases in the system.
 */
@Repository
public interface PurchaseRepo extends JpaRepository<Purchase,Integer> {
  /**
   * Deletes a purchase by coupon ID.
   *
   * @param couponId the ID of the coupon associated with the purchase
   */
  void deleteByCouponId(int couponId);
  
  
  /**
   * Deletes purchases by customer ID.
   *
   * @param customerId the ID of the customer associated with the purchases
   * @return the number of deleted purchases
   */
  int deleteByCustomerId(int customerId);
  
  
  /**
   * Retrieves a purchase by customer ID and coupon ID.
   *
   * @param customerId the ID of the customer
   * @param couponId the ID of the coupon
   * @return the retrieved purchase, or null if not found
   */
  Purchase findByCustomerIdAndCouponId(int customerId, int couponId);
  
  
}
