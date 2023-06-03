/**
 
 Validator class for CouponUser entity.
 */
package com.JbSchool.coupons3.security.config;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;
@Component
@RequiredArgsConstructor
public class CouponUserValidator {
  
  
  private final CouponUserRepo couponUserRepo;
  
  /**
   
   Validates and updates the email of a CouponUser.
   @param oldEmail the old email of the CouponUser
   @param newEmail the new email to be updated
   @throws CouponException if the new email already exists
   */
  public void updateEmail(String oldEmail , String newEmail) throws CouponException {
    CouponUser couponUserFromDb = this.couponUserRepo.findByUsername(oldEmail);
    if (this.couponUserRepo.existsByUsernameAndIdNot(newEmail,couponUserFromDb.getId())) {
      throw new CouponException(ErrorMessageProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
  }
  
  /**
   
   Validates the addition of a new email for a CouponUser.
   @param newEmail the new email to be added
   @throws CouponException if the new email already exists
   */
  public void addEmail(String newEmail) throws CouponException {
    if (this.couponUserRepo.existsByUsername(newEmail)) {
      throw new CouponException(ErrorMessageProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
  }
}
