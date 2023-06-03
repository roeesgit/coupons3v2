/**
 
 CouponUserAuthProvider class is responsible for setting up the authentication roles for different types of users.
 */
package com.JbSchool.coupons3.security.config;

import com.JbSchool.coupons3.security.entites.auth.*;
import com.JbSchool.coupons3.security.entites.coupon_users_auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Component
@RequiredArgsConstructor
public class CouponUserAuthProvider {
  
  private final CouponAuthRepo     couponAuthRepo;
  private final CouponUserAuthRepo couponUserAuthRepo;
  
  /**
   
   Sets up the authentication roles for an admin user.
   @param admin the CouponUser object representing the admin user
   */
  public void setAuthForAdmin(CouponUser admin) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    
    CouponUserAuth roleAdmin = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(0)).couponUser(admin).build();
    this.couponUserAuthRepo.save(roleAdmin);
    CouponUserAuth specialPermission = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(6)).couponUser(admin).build();
    this.couponUserAuthRepo.save(specialPermission);
  }
  
  /**
   
   Sets up the authentication roles for a company user.
   @param company the CouponUser object representing the company user
   */
  public void setAuthForCompany(CouponUser company) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    CouponUserAuth roleCompany = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(1)).couponUser(company).build();
    this.couponUserAuthRepo.save(roleCompany);
  }
  
  /**
   
   Sets up the authentication roles for a customer user.
   @param customer the CouponUser object representing the customer user
   */
  public void setAuthForCustomer(CouponUser customer) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    
    CouponUserAuth roleCustomer = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(2)).couponUser(customer).build();
    this.couponUserAuthRepo.save(roleCustomer);
  }
  
  
}
