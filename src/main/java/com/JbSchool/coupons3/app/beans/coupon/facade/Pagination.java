package com.JbSchool.coupons3.app.beans.coupon.facade;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.stream.*;

/**
 * This class provides pagination functionality for coupons.
 * It interacts with the CouponRepo to retrieve coupons using pagination.
 */
@RequiredArgsConstructor @Component
public class Pagination {
  private final CouponRepo couponRepo;
  
  
  /**
   * Retrieves a page of coupons and prints them.
   * Uses pagination to retrieve a specific page of coupons from the CouponRepo.
   */
public void hh(){
  Page <Coupon> couponPage = this.couponRepo.findAll(PageRequest.of(2, 12));
  couponPage.get().toList().forEach(System.out::println);
  System.out.println("Done");
}
}
