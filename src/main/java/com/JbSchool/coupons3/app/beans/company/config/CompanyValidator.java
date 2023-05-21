package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.stereotype.*;

@Component
@RequiredArgsConstructor
public class CompanyValidator {
  
  private final CompanyRepo  companyRepo;
  private final PurchaseRepo        purchaseRepo;
  private final CouponUserValidator couponUserValidator;
  
  
 
  public Company getOptionalCompany(int id) throws CouponException {
    return this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
  }
  
  public void addCompany(Company company) throws CouponException {
    if (company.getId()!=0) {
      throw new CouponException(ErrorMessageProvider.ID_MUST_BE_EMPTY.getMessage());
    }
   companyParams(company,company.getId(),true);
  }
  public void updateCompany(Company company,int id) throws CouponException {
   companyParams(company,id,false);
  }
  
  private void companyParams(Company company , int id , boolean isAdd) throws CouponException {
    Company companyFromDb = getOptionalCompany(id);
    if (isAdd) {
      this.couponUserValidator.addEmail(company.getEmail());
    }else {
      this.couponUserValidator.updateEmail(companyFromDb.getEmail() , company.getEmail());
    }
    if (this.companyRepo.existsByNameAndIdNot(company.getName(),id)) {
      throw new CouponException(ErrorMessageProvider.COMPANY_NAME_EXIST.getMessage());
    }
  }
  
  
  
}
