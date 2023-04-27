package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.company.facade.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Service @RequiredArgsConstructor
public class CouponService {
  
  private final   CouponRepo couponRepo;
  private final Mapper     mapper;
  
  
  public CouponDto addCoupon(Coupon coupon) {
    coupon.setCompanyId(this.mapper.companyIdFromSCH());
   this.couponRepo.save(coupon);
    return this.mapper.couponToCouponDto(coupon);
  }
  public void updateCoupon(Coupon coupon,int id) throws CouponException {
    int companyId = this.mapper.companyIdFromSCH();
    if (this.couponRepo.existsById(id)) {
      throw new CouponException(CouponExceptionProvider.COUPON_ID_DOES_NOT_EXIST.getMessage());
    }
    if (!this.couponRepo.existsByCompanyId(companyId)) {
      throw new CouponException(CouponExceptionProvider.COUPON_NOT_OWNED_BY_COMPANY.getMessage());
    }
    coupon.setId(id);
    coupon.setCompanyId(companyId);
   this.couponRepo.save(coupon);
  }
  public Coupon getCoupon(int id) {
   return this.couponRepo.findById(id).orElseThrow();
  }
  public void removeCoupon(int id) {
   this.couponRepo.deleteById(id);
  }
  
  
  public List<Coupon> getAllCoupons() {
    return this.couponRepo.findAll();
  }
  
  
}
