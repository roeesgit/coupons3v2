package com.JbSchool.coupons3.app.beans.purchase;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service
@RequiredArgsConstructor
public class PurchaseService {
  
  private final PurchaseRepo purchaseRepo;
  
  
  public void addPurchase(Purchase purchase) {
     this.purchaseRepo.save(purchase);
  }
  
  
  public void updatePurchase(Purchase purchase, int id) {
    this.purchaseRepo.save(purchase);
  }
  
  
  public Purchase getPurchase(int id) {
    return this.purchaseRepo.findById(id).orElseThrow();
  }
  
  
  public void removePurchase(int id) {
    this.purchaseRepo.deleteById(id);
  }
  
  
  public void removePurchaseByCouponId(int couponId) {
    this.purchaseRepo.deleteByCouponId(couponId);
  }
  
  
  public void removePurchaseByCustomerId(int customerId) {
    this.purchaseRepo.deleteByCustomerId(customerId);
  }
  
  
 
  
  
}
