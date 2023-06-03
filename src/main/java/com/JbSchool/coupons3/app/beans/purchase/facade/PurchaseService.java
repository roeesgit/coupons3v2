package com.JbSchool.coupons3.app.beans.purchase.facade;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Service class for managing purchases.
 */
@Service
@RequiredArgsConstructor
public class PurchaseService {
  
  private final PurchaseRepo purchaseRepo;
  
  /**
   * Adds a new purchase.
   *
   * @param purchase the purchase to be added
   */
  public void addPurchase(Purchase purchase) {
     this.purchaseRepo.save(purchase);
  }
  
  /**
   * Updates an existing purchase.
   *
   * @param purchase the updated purchase
   * @param id the ID of the purchase to be updated
   */
  public void updatePurchase(Purchase purchase, int id) {
    this.purchaseRepo.save(purchase);
  }
  
  /**
   * Retrieves a purchase by ID.
   *
   * @param id the ID of the purchase
   * @return the retrieved purchase
   * @throws NoSuchElementException if the purchase is not found
   */
  public Purchase getPurchase(int id) {
    return this.purchaseRepo.findById(id).orElseThrow();
  }
  
  /**
   * Removes a purchase by ID.
   *
   * @param id the ID of the purchase to be removed
   */
  public void removePurchase(int id) {
    this.purchaseRepo.deleteById(id);
  }
  
  /**
   * Removes purchases by coupon ID.
   *
   * @param couponId the ID of the coupon associated with the purchases to be removed
   */
  public void removePurchaseByCouponId(int couponId) {
    this.purchaseRepo.deleteByCouponId(couponId);
  }
  
  /**
   * Removes purchases by customer ID.
   *
   * @param customerId the ID of the customer associated with the purchases to be removed
   */
  public void removePurchaseByCustomerId(int customerId) {
    this.purchaseRepo.deleteByCustomerId(customerId);
  }
  
  
 
  
  
}
