package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.category.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * The CouponController class handles the REST API endpoints for managing coupons.
 * It provides methods to add, update, delete, and retrieve coupons based on different criteria.
 * The controller communicates with the CouponServiceImpl to perform the necessary operations.
 */
@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {
  
  private final CouponServiceImpl couponServiceImpl;
  private final Mapper mapper;
  
  /**
   * Adds a new coupon to the system.
   *
   * @param coupon The coupon to be added (request body).
   * @return The added coupon details (CouponDto).
   * @throws CouponException If an error occurs while adding the coupon.
   */
  @PostMapping
  public CouponDto addCoupon(@RequestBody @Valid Coupon coupon) throws CouponException {
    return this.couponServiceImpl.addCoupon(coupon);
  }
  
  /**
   * Updates an existing coupon in the system.
   *
   * @param coupon The updated coupon details (request body).
   * @param id     The ID of the coupon to be updated.
   * @throws CouponException If an error occurs while updating the coupon.
   */
  @PutMapping("/{id}")
  public void updateCoupon(@RequestBody @Valid Coupon coupon, @PathVariable int id) throws CouponException {
    this.couponServiceImpl.updateCoupon(coupon, id);
  }
  
  /**
   * Retrieves the details of a coupon by its ID.
   *
   * @param id The ID of the coupon.
   * @return The coupon details (CouponDto).
   * @throws CouponException If the coupon with the specified ID is not found.
   */
  @GetMapping("/customer/{id}")
  public CouponDto getCouponById(@PathVariable int id) throws CouponException {
    return this.couponServiceImpl.getCouponById(id);
  }
  
  /**
   * Retrieves all coupons in the system.
   *
   * @return A list of all coupons (List<CouponDto>).
   */
  @GetMapping("/all")
  public List<CouponDto> getAll() {
    return this.couponServiceImpl.getAll();
  }
  
  /**
   * Deletes a coupon from the system.
   *
   * @param id The ID of the coupon to be deleted.
   * @throws CouponException If an error occurs while deleting the coupon.
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id) throws CouponException {
    this.couponServiceImpl.deleteCoupon(id);
  }
  
  /**
   * Retrieves all coupons belonging to the company.
   *
   * @return A list of company coupons (List<CouponDto>).
   */
  @GetMapping("/company")
  public List<CouponDto> getCompanyCoupons() {
    return this.couponServiceImpl.getCompanyCoupons();
  }
  
  /**
   * Retrieves company coupons based on the specified category.
   *
   * @param category The category of the coupons.
   * @return A list of company coupons in the specified category (List<CouponDto>).
   */
  @GetMapping("/company/byCategory/{category}")
  public List<CouponDto> getCompanyCouponsByCategory(@PathVariable String category) {
    return this.couponServiceImpl.getCompanyCouponsByCategory(CategoryProvider.valueOf(category.toUpperCase()));
  }
  
  /**
   * Retrieves company coupons with a price less than or equal to the specified price.
   *
   * @param price The maximum price of the coupons.
   * @return A list of company coupons with a price less than or equal to the specified price (List<CouponDto>).
   */
  @GetMapping("/company/byPrice/{price}")
  public List<CouponDto> getCompanyCouponsByPrice(@PathVariable int price) {
    return this.couponServiceImpl.getCompanyCouponsByPrice(price);
  }
  
  /**
   * Retrieves all coupons belonging to the customer.
   *
   * @return A list of customer coupons (List<CouponDto>).
   */
  @GetMapping("/customer")
  public List<CouponDto> getCustomerCoupons() {
    return this.couponServiceImpl.getCustomerCoupons();
  }
  
  /**
   * Retrieves customer coupons based on the specified category.
   *
   * @param category The category of the coupons.
   * @return A list of customer coupons in the specified category (List<CouponDto>).
   */
  @GetMapping("/customer/category/{category}")
  public List<CouponDto> getCustomerCouponsByCategory(@PathVariable String category) {
    return this.couponServiceImpl.getCustomerCouponsByCategory(CategoryProvider.valueOf(category.toUpperCase()));
  }
  
  /**
   * Retrieves customer coupons with a price less than or equal to the specified price.
   *
   * @param price The maximum price of the coupons.
   * @return A list of customer coupons with a price less than or equal to the specified price (List<CouponDto>).
   */
  @GetMapping("/customer/byPrice/{price}")
  public List<CouponDto> getCustomerCouponsByPrice(@PathVariable int price) {
    return this.couponServiceImpl.getCustomerCouponsByPrice(price);
  }
  
  /**
   * Retrieves valid coupons that a customer can purchase.
   *
   * @return A list of valid coupons for purchase by the customer (List<CouponDto>).
   */
  @GetMapping("/customer/validToBuy")
  public List<CouponDto> getValidCouponTOPurchaseForCustomer() {
    return this.couponServiceImpl.getValidCouponTOPurchaseForCustomer();
  }
  
  /**
   * Allows a customer to buy a coupon.
   *
   * @param coupon The coupon to be purchased (request body).
   * @throws CouponException If an error occurs while purchasing the coupon.
   */
  @PostMapping("/customer/buyCoupon")
  public void buyCoupon(@RequestBody Coupon coupon) throws CouponException {
    this.couponServiceImpl.buyCoupon(coupon, this.mapper.userIdFromSCH());
  }
}
