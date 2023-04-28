package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
@Component
@RequiredArgsConstructor
public class CouponExcValidator {
  
  private final CouponRepo   couponRepo;
  private final CompanyRepo  companyRepo;
  private final CustomerRepo customerRepo;
  private final PurchaseRepo purchaseRepo;
  
  
  public void ownedByCompany(int couponId, int companyId) throws CouponException {
    if (!this.couponRepo.existsByIdAndCompanyId(couponId, companyId)) {
      throw new CouponException(CouponExceptionProvider.COUPON_NOT_OWNED_BY_COMPANY.getMessage());
    }
  }
  
  
  public Coupon getOptionalCoupon(int id) throws CouponException {
    return this.couponRepo.findById(id)
      .orElseThrow(() -> new CouponException(CouponExceptionProvider.COUPON_ID_DOES_NOT_EXIST.getMessage()));
  }
  
  
  public Company getOptionalCompany(int id) throws CouponException {
    return this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  
  public Customer getOptionalCustomer(int id) throws CouponException {
    return this.customerRepo.findById(id)
      .orElseThrow(() -> new CouponException(CustomerExceptionProvider.CUSTOMER_NOT_FOUND.getMessage()));
  }
  
  
  public void buyCoupon(Coupon coupon, int customerId) throws CouponException {
    List <Coupon> purchaseCoupons = couponRepo.getCustomerCoupons(customerId);
    if (purchaseCoupons.contains(coupon)) {
      throw new CouponException(CouponExceptionProvider.COUPON_ALREADY_OWNED_BY_CUSTOMER.getMessage());
    }
    if (coupon.getAmount() < 1) {
      throw new CouponException(CouponExceptionProvider.OUT_OF_STOCK.getMessage());
    }
    if (coupon.getEndDate().isBefore(LocalDate.now())) {
      throw new CouponException(CouponExceptionProvider.COUPON_EXPIRED.getMessage());
    }
  }
  
  
  
}
