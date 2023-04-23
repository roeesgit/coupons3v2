package com.JbSchool.coupons3.app.beans.purchase;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
@Service @RequiredArgsConstructor
public class PurchaseService {
  
  private final PurchaseRepo purchaseRepo;
  
  
  public Purchase addPurchase(Purchase purchase) {
   return this.purchaseRepo.save(purchase);
  }
  public void updatePurchase(Purchase purchase,int id) {
   this.purchaseRepo.save(purchase);
  }
  public Purchase getPurchase(int id) {
   return this.purchaseRepo.findById(id).orElseThrow();
  }
  public void removePurchase(int id) {
   this.purchaseRepo.deleteById(id);
  }
}
