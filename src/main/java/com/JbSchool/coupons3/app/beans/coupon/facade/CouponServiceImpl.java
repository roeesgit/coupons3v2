package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.purchase.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
  
  private final CouponRepo couponRepo;
  
  private final Mapper          mapper;
  private final CouponValidator couponValidator;
  private final PurchaseService purchaseService;
  
  
  @Override
  public CouponDto addCoupon(Coupon coupon) throws CouponException {
    coupon.setCompanyId(mapper.userIdFromSCH());
    this.couponValidator.addCoupon(coupon);
    this.couponRepo.save(coupon);
    return this.mapper.couponToCouponDto(coupon);
  }
  
  //todo ולידציה
  @Override
  public void updateCoupon(Coupon coupon, int id)   {
    int companyId = mapper.userIdFromSCH();
    coupon.setId(id);
    coupon.setCompanyId(companyId);
    this.couponRepo.save(coupon);
  }


  
  @Override
  @Transactional
  public void deleteCoupon(int id) throws CouponException {
    this.couponValidator.deleteCoupon(id ,this.mapper.userIdFromSCH());
    this.purchaseService.removePurchaseByCouponId(id);
    this.couponRepo.deleteById(id);
  }
  

  
  @Override
  public List <Coupon> getCompanyCoupons() {
    return this.couponRepo.findByCompanyId(mapper.userIdFromSCH());
  }
  
  
  @Override
  public List <Coupon> getCompanyCouponsByCategory(CategoryProvider categoryProvider) {
    return this.couponRepo.findByCompanyIdAndCategory(mapper.userIdFromSCH(), categoryProvider);
  }
  
  
  @Override
  public List <Coupon> getCompanyCouponsByPrice(int price) {
    return this.couponRepo.findByCompanyIdAndPriceLessThanEqual(mapper.userIdFromSCH(), price);
  }
  
  
  @Override
  public List <Coupon> getCustomerCoupons() {
    return this.couponRepo.getCustomerCoupons(mapper.userIdFromSCH());
  }
  
  @Override
  public List <Coupon> getCustomerCouponsByCategory(CategoryProvider categoryProvider) {
    return this.couponRepo.getCustomerCouponsByCategory(mapper.userIdFromSCH(),categoryProvider);
  }
  
  
  @Override
  public List <Coupon> getCustomerCouponsByPrice(int  price) {
    return this.couponRepo.getCustomerCoupons(mapper.userIdFromSCH());
  }
  
  
  @Override
  public List <Coupon> getValidCouponTOPurchaseForCustomer() {
    return this.couponRepo.getValidCouponTOPurchaseForCustomer(mapper.userIdFromSCH());
  }
  @Override
  @Transactional
  public void buyCoupon(Coupon coupon, int customerId) throws CouponException {
    Coupon couponFromDb = this.couponValidator.getOptionalCoupon(coupon.getId());
    this.couponValidator.buyCoupon(couponFromDb,customerId);
    couponFromDb.setAmount(couponFromDb.getAmount() - 1);
    Purchase purchase = Purchase.builder().customerId(customerId).couponId(couponFromDb.getId()).build();
    this.purchaseService.addPurchase(purchase);
    this.couponRepo.save(couponFromDb);
  }
  
  @Override
  public List<CouponDto> getAll() {
    return  this.couponRepo.findAll().stream().map(mapper::couponToCouponDto).collect(Collectors.toList());
  }
  
  @Scheduled(cron ="@midnight")
  @Transactional
  public void deleteExpiredCoupons() {
    this.couponRepo.deleteByEndDateLessThan(LocalDate.now());
  }
  
}
