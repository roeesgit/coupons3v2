package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.config.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;
/**
 The CouponRepo interface provides data access methods for the Coupon entity.
 */
@Repository
public interface CouponRepo extends JpaRepository <Coupon, Integer> {
  
  /**
   Checks if a coupon with the given title and company ID exists.
   
   @param title the title of the coupon
   @param companyId the ID of the company
   @return true if the coupon exists, false otherwise
   */
  boolean existsByTitleAndCompanyId(String title, int companyId);
  
  /**
   Checks if a coupon with the given ID and company ID exists.
   
   @param couponId the ID of the coupon
   @param companyId the ID of the company
   @return true if the coupon exists, false otherwise
   */
  boolean existsByIdAndCompanyId(int couponId, int companyId);
  
  /**
   Retrieves a list of coupons by company ID and category.
   
   @param companyId the ID of the company
   @param category the category of the coupon
   @return a list of coupons
   */
  List <Coupon> findByCompanyIdAndCategory(int companyId, CategoryProvider category);
  
  /**
   Retrieves a list of coupons by company ID and price less than or equal to the specified price.
   
   @param companyId the ID of the company
   @param price the maximum price of the coupon
   @return a list of coupons
   */
  List <Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, int price);
  
  /**
   Deletes coupons with end date less than the specified date.
   
   @param endDate the end date
   */
  void deleteByEndDateLessThan(LocalDate endDate);
  
  /**
   Retrieves a list of coupons by company ID.
   
   @param companyId the ID of the company
   @return a list of coupons
   */
  List <Coupon> findByCompanyId(int companyId);
  
  /**
   Retrieves a list of customer's coupons by user ID.
   
   @param customerId the ID of the user
   @return a list of coupons
   */
  
  @Query(nativeQuery = true, value = """
    select * from coupons
    where
    id in(select coupon_id from purchases where customer_id = ? )\s
    group by id;"""
  )
  List <Coupon> getCustomerCoupons(int customerId);
  
  /**
   Retrieves a list of customer's coupons by user ID and category.
   
   @param customerId the ID of the user
   @param category the category of the coupon
   @return a list of coupons
   */
  @Query(nativeQuery = true, value = """
    select * from coupons\s
    where
    id in(select coupon_id from purchases where customer_id = ? ) \s
    and category = ?\s
    group by id;"""
  )
  List <Coupon> getCustomerCouponsByCategory(int customerId, CategoryProvider category);
  
  /**
   /**
   Retrieves a list of valid coupons available for purchase by a customer.
   
   @param customerId the ID of the user
   @return a list of coupons
   */
  
  @Query(nativeQuery = true, value = """
    select * from coupons
    where
    id not in(select coupon_id from purchases where customer_id = ? )\s
    and
    amount > 0\s
    and end_date >= now()\s
    group by id;"""
  )
  List <Coupon> getValidCouponTOPurchaseForCustomer(int customerId);
  
  /**
   Deletes coupons by company ID.
   
   @param id the ID of the company
   @return the number of deleted coupons
   */
  int deleteByCompanyId(int id);
  
  /**
   Deletes purchases of company coupons by company ID.
   
   @param companyId the ID of the company
   */
  @Transactional
  @Modifying
  @Query(nativeQuery = true, value =
    "delete from purchases where coupon_id in (select id from coupons where company_id = ? ) "
  )
  void deleteCompanyCouponsPurchases(int companyId);
  
  /**
   Checks if a coupon with the given title, ID (excluding), and company ID exists.
   
   @param title the title of the coupon
   @param id the ID of the coupon to exclude
   @param companyId the ID of the company
   @return true if the coupon exists, false otherwise
   */
  boolean existsByTitleAndIdNotAndCompanyId(String title, int id, int companyId);
  
  
  
  
  
}
