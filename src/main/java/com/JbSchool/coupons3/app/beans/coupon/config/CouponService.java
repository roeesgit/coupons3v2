package com.JbSchool.coupons3.app.beans.coupon.config;

import lombok.*;
import org.springframework.stereotype.*;
@Service @RequiredArgsConstructor
public class CouponService {
  
  private final CouponRepo couponRepo;
  
  
  public Coupon addCoupon(Coupon coupon) {
   return this.couponRepo.save(coupon);
  }
  public void updateCoupon(Coupon coupon,int id) {
   this.couponRepo.save(coupon);
  }
  public Coupon getCoupon(int id) {
   return this.couponRepo.findById(id).orElseThrow();
  }
  public void removeCoupon(int id) {
   this.couponRepo.deleteById(id);
  }
  public int countByCompany_Id(int companyId) {
  return this.couponRepo.countByCompanyId(companyId);
  }
}
