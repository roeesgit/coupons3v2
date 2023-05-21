package com.JbSchool.coupons3.security.config;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;
@Component
@RequiredArgsConstructor
public class CouponUserValidator {
  
  
  private final CouponUserRepo couponUserRepo;
  
  
  public void updateEmail(String oldEmail , String newEmail) throws CouponException {
    CouponUser couponUserFromDb = this.couponUserRepo.findByUsername(oldEmail);
    if (this.couponUserRepo.existsByUsernameAndIdNot(newEmail,couponUserFromDb.getId())) {
      throw new CouponException(ErrorMessageProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
  }
  public void addEmail(String newEmail) throws CouponException {
    if (this.couponUserRepo.existsByUsername(newEmail)) {
      throw new CouponException(ErrorMessageProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
  }
}
