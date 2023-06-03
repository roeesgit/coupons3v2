package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
@Component
@RequiredArgsConstructor
public class CouponValidator {
  
  private final CouponRepo   couponRepo;
  private final PurchaseRepo purchaseRepo;
  
  
  public Coupon getOptionalCoupon(int id) throws CouponException {
    return this.couponRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  
  public void buyCoupon(Coupon coupon, int customerId) throws CouponException {
//    List <Coupon> purchaseCoupons = couponRepo.getCustomerCoupons(customerId);
    Purchase existingCoupon = this.purchaseRepo.findByCustomerIdAndCouponId(customerId, coupon.getId());
    System.out.println("/**********************************");
//    purchaseCoupons.forEach(System.out::println);
    System.out.println("coupon : " + coupon);
//    if (purchaseCoupons.contains(coupon)) {
    if (existingCoupon != null) {
      throw new CouponException(ErrorMessageProvider.COUPON_ALREADY_OWNED_BY_CUSTOMER.getMessage());
    }
    if (coupon.getAmount() < 1) {
      throw new CouponException(ErrorMessageProvider.OUT_OF_STOCK.getMessage());
    }
    if (coupon.getEndDate().isBefore(LocalDate.now())) {
      throw new CouponException(ErrorMessageProvider.COUPON_EXPIRED.getMessage());
    }
  }
  
  
  public void addCoupon(Coupon coupon) throws CouponException {
    if (coupon.getId() != 0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
    existsByTitleAndIdNotAndCompanyId(coupon);
  }
  
  
  public void updateCoupon(Coupon coupon) throws CouponException {
    ownedByCompany(coupon.getId(), coupon.getCompanyId());
    existsByTitleAndIdNotAndCompanyId(coupon);
  }
  
  
  public void ownedByCompany(int couponId, int companyId) throws CouponException {
    if (!this.couponRepo.existsByIdAndCompanyId(couponId, companyId)) {
      throw new CouponException(ErrorMessageProvider.COUPON_NOT_OWNED_BY_COMPANY.getMessage());
    }
  }
  
  
  public void existsByTitleAndIdNotAndCompanyId(Coupon coupon) throws CouponException {
    if (this.couponRepo.existsByTitleAndIdNotAndCompanyId(coupon.getTitle(), coupon.getId(), coupon.getCompanyId())) {
      throw new CouponException(ErrorMessageProvider.COUPON_TITLE_ALREADY_EXIST.getMessage());
    }
    
  }
  
  
  public void deleteCoupon(int id, int companyID) throws CouponException {
    ownedByCompany(id, companyID);
  }
  
  
}
