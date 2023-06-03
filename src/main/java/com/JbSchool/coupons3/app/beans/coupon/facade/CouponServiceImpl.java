package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.category.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.beans.purchase.facade.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * This class implements the CouponService interface and provides the functionality for managing coupons.
 * It interacts with the CouponRepo, Mapper, CouponValidator, and PurchaseService to perform the necessary operations.
 */
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
  
  private final CouponRepo couponRepo;
  
  private final Mapper          mapper;
  private final CouponValidator couponValidator;
  private final PurchaseService purchaseService;
  
  /**
   * Adds a coupon to the system.
   *
   * @param coupon The coupon to be added.
   * @return The added coupon as a CouponDto.
   * @throws CouponException If an error occurs while adding the coupon.
   */
  @Override
  public CouponDto addCoupon(Coupon coupon) throws CouponException {
    coupon.setCompanyId(mapper.userIdFromSCH());
    this.couponValidator.addCoupon(coupon);
    this.couponRepo.save(coupon);
    return this.mapper.couponToCouponDto(coupon);
  }
  
  /**
   * Updates a coupon in the system.
   *
   * @param coupon The updated coupon.
   * @param id     The ID of the coupon to update.
   * @throws CouponException If an error occurs while updating the coupon.
   */
  @Override
  public void updateCoupon(Coupon coupon, int id) throws CouponException {
    int companyId = mapper.userIdFromSCH();
    this.couponValidator.updateCoupon(coupon);
    coupon.setId(id);
    coupon.setCompanyId(companyId);
    this.couponRepo.save(coupon);
  }
  
  /**
   * Deletes a coupon from the system.
   *
   * @param id The ID of the coupon to delete.
   * @throws CouponException If an error occurs while deleting the coupon.
   */
  @Override
  @Transactional
  public void deleteCoupon(int id) throws CouponException {
    this.couponValidator.deleteCoupon(id, this.mapper.userIdFromSCH());
    this.purchaseService.removePurchaseByCouponId(id);
    this.couponRepo.deleteById(id);
  }
  
  /**
   * Retrieves all coupons belonging to the company.
   *
   * @return A list of CouponDto representing the company's coupons.
   */
  @Override
  public List <CouponDto> getCompanyCoupons() {
    return this.mapper.couponsToCouponsDto(this.couponRepo.findByCompanyId(mapper.userIdFromSCH()));
  }
  
  /**
   * Retrieves company coupons by category.
   *
   * @param categoryProvider The category provider.
   * @return A list of CouponDto representing the company's coupons in the specified category.
   */
  @Override
  public List <CouponDto> getCompanyCouponsByCategory(CategoryProvider categoryProvider) {
    return this.mapper.couponsToCouponsDto(this.couponRepo.findByCompanyIdAndCategory(mapper.userIdFromSCH(), categoryProvider));
  }
  
  /**
   * Retrieves company coupons by price.
   *
   * @param price The maximum price of the coupons.
   * @return A list of CouponDto representing the company's coupons within the specified price range.
   */
  @Override
  public List <CouponDto> getCompanyCouponsByPrice(int price) {
    return this.mapper.couponsToCouponsDto(this.couponRepo.findByCompanyIdAndPriceLessThanEqual(mapper.userIdFromSCH(), price));
  }
  
  /**
   * Retrieves all coupons belonging to the customer.
   *
   * @return A list of CouponDto representing the customer's coupons.
   */
  @Override
  public List <CouponDto> getCustomerCoupons() {
    return this.mapper.couponsToCouponsDto(this.couponRepo.getCustomerCoupons(mapper.userIdFromSCH()));
  }
  
  /**
   * Retrieves customer coupons by category.
   *
   * @param categoryProvider The category provider.
   * @return A list of CouponDto representing the customer's coupons in the specified category.
   */
  @Override
  public List <CouponDto> getCustomerCouponsByCategory(CategoryProvider categoryProvider) {
    return this.mapper.couponsToCouponsDto(this.couponRepo.getCustomerCouponsByCategory(mapper.userIdFromSCH(), categoryProvider));
  }
  
  /**
   * Retrieves customer coupons by price.
   *
   * @param price The maximum price of the coupons.
   * @return A list of CouponDto representing the customer's coupons within the specified price range.
   */
  @Override
  public List <CouponDto> getCustomerCouponsByPrice(int price) {
    return this.mapper.couponsToCouponsDto(this.couponRepo.getCustomerCoupons(mapper.userIdFromSCH()));
  }
  
  /**
   * Retrieves valid coupons that a customer can purchase.
   *
   * @return A list of CouponDto representing the valid coupons available for purchase.
   */
  @Override
  public List <CouponDto> getValidCouponTOPurchaseForCustomer() {
    List <Coupon> coupons = this.couponRepo.getValidCouponTOPurchaseForCustomer(mapper.userIdFromSCH());
    return this.mapper.couponsToCouponsDto(this.couponRepo.getValidCouponTOPurchaseForCustomer(mapper.userIdFromSCH()));
  }
  
  /**
   * Buys a coupon for the customer.
   *
   * @param coupon     The coupon to buy.
   * @param customerId The ID of the customer.
   * @throws CouponException If an error occurs while buying the coupon.
   */
  @Override
  @Transactional
  public void buyCoupon(Coupon coupon, int customerId) throws CouponException {
    Coupon couponFromDb = this.couponValidator.getOptionalCoupon(coupon.getId());
    this.couponValidator.buyCoupon(couponFromDb, customerId);
    couponFromDb.setAmount(couponFromDb.getAmount() - 1);
    Purchase purchase = Purchase.builder().customerId(customerId).couponId(couponFromDb.getId()).build();
    this.purchaseService.addPurchase(purchase);
    this.couponRepo.save(couponFromDb);
  }
  
  /**
   * Retrieves all coupons.
   *
   * @return A list of all CouponDto representing all the coupons in the system.
   */
  @Override
  public List <CouponDto> getAll() {
    return this.couponRepo.findAll().stream().map(mapper::couponToCouponDto).collect(Collectors.toList());
  }
  
  /**
   * Deletes expired coupons from the system.
   * This method is scheduled to run daily at midnight.
   */
  @Scheduled(cron = "@midnight")
  @Transactional
  public void deleteExpiredCoupons() {
    this.couponRepo.deleteByEndDateLessThan(LocalDate.now());
  }
  /**
   * Retrieves a coupon by its ID.
   *
   * @param id The ID of the coupon.
   * @return The coupon as a CouponDto.
   * @throws CouponException If the coupon with the given ID does not exist.
   */
  @Override
  public CouponDto getCouponById(int id) throws CouponException {
    return this.mapper.couponToCouponDto(this.couponValidator.getOptionalCoupon(id));
  }
  
  
}
