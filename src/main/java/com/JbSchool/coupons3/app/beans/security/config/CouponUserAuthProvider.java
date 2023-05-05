package com.JbSchool.coupons3.app.beans.security.config;

import com.JbSchool.coupons3.app.beans.security.entites.auth.*;
import com.JbSchool.coupons3.app.beans.security.entites.coupon_users_auth.*;
import com.JbSchool.coupons3.app.beans.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;
@Component
@RequiredArgsConstructor
public class CouponUserAuthProvider {
  
  private final CouponAuthRepo     couponAuthRepo;
  private final CouponUserAuthRepo couponUserAuthRepo;
  
  
  public void setAuthForAdmin(CouponUser admin) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    
    CouponUserAuth roleAdmin = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(0)).couponUser(admin).build();
    this.couponUserAuthRepo.save(roleAdmin);
    CouponUserAuth specialPermission = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(6)).couponUser(admin).build();
    this.couponUserAuthRepo.save(specialPermission);
  }
  
  
  public void setAuthForCompany(CouponUser company) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    System.out.println("********* Test 4 **********");
    
    CouponUserAuth roleCompany = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(1)).couponUser(company).build();
    this.couponUserAuthRepo.save(roleCompany);
  }
  
  
  public void setAuthForCustomer(CouponUser customer) {
    List <CouponAuth> couponAuths = this.couponAuthRepo.findAll();
    
    CouponUserAuth roleCustomer = CouponUserAuth.builder()
      .couponAuth(couponAuths.get(2)).couponUser(customer).build();
    this.couponUserAuthRepo.save(roleCustomer);
  }
  
  
}
