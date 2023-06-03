package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.time.*;
/**
 * The CouponValidator class provides validation methods for coupons.
 */
@Component
@RequiredArgsConstructor
public class CouponValidator {
  
  private final CouponRepo couponRepo;
  private final PurchaseRepo purchaseRepo;
  
  /**
   * Retrieves an optional coupon by ID.
   *
   * @param id the ID of the coupon
   * @return the coupon
   * @throws CouponException if the coupon is not found
   */
  public Coupon getOptionalCoupon(int id) throws CouponException {
    return this.couponRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  /**
   * Validates the purchase of a coupon.
   *
   * @param coupon     the coupon to purchase
   * @param customerId the ID of the customer
   * @throws CouponException if an error occurs while buying the coupon
   */
  public void buyCoupon(Coupon coupon, int customerId) throws CouponException {
    if (coupon.getAmount() < 1) {
      throw new CouponException(ErrorMessageProvider.OUT_OF_STOCK.getMessage());
    }
    if (coupon.getEndDate().isBefore(LocalDate.now())) {
      throw new CouponException(ErrorMessageProvider.COUPON_EXPIRED.getMessage());
    }
    Purchase existingCoupon = this.purchaseRepo.findByCustomerIdAndCouponId(customerId, coupon.getId());
    if (existingCoupon != null) {
      throw new CouponException(ErrorMessageProvider.COUPON_ALREADY_OWNED_BY_CUSTOMER.getMessage());
    }
  }
  
  /**
   * Validates the addition of a coupon.
   *
   * @param coupon the coupon to add
   * @throws CouponException if an error occurs while adding the coupon
   */
  public void addCoupon(Coupon coupon) throws CouponException {
    if (coupon.getId() != 0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
    existsByTitleAndIdNotAndCompanyId(coupon);
  }
  
  /**
   * Validates the update of a coupon.
   *
   * @param coupon the updated coupon
   * @throws CouponException if an error occurs while updating the coupon
   */
  public void updateCoupon(Coupon coupon) throws CouponException {
    ownedByCompany(coupon.getId(), coupon.getCompanyId());
    existsByTitleAndIdNotAndCompanyId(coupon);
  }
  
  /**
   * Validates if the coupon is owned by the company.
   *
   * @param couponId  the ID of the coupon
   * @param companyId the ID of the company
   * @throws CouponException if the coupon is not owned by the company
   */
  public void ownedByCompany(int couponId, int companyId) throws CouponException {
    if (!this.couponRepo.existsByIdAndCompanyId(couponId, companyId)) {
      throw new CouponException(ErrorMessageProvider.COUPON_NOT_OWNED_BY_COMPANY.getMessage());
    }
  }
  
  /**
   * Validates if a coupon with the same title already exists for a different coupon ID and company ID.
   *
   * @param coupon the coupon to validate
   * @throws CouponException if a coupon with the same title already exists
   */
  public void existsByTitleAndIdNotAndCompanyId(Coupon coupon) throws CouponException {
    if (this.couponRepo.existsByTitleAndIdNotAndCompanyId(coupon.getTitle(), coupon.getId(), coupon.getCompanyId())) {
      throw new CouponException(ErrorMessageProvider.COUPON_TITLE_ALREADY_EXIST.getMessage());
    }
  }
  
  /**
   * Validates the deletion of a coupon by
   
   ID and company ID.
   *
   * @param id       the ID of the coupon
   * @param companyID the ID of the company
   * @throws CouponException if the coupon is not owned by the company
   */
  public void deleteCoupon(int id, int companyID) throws CouponException {
    ownedByCompany(id, companyID);
  }
}