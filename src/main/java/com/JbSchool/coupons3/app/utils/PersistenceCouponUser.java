package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.security.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
@Component @RequiredArgsConstructor
public class PersistenceCouponUser {
  private final PasswordEncoder passwordEncoder;
  private final   CompanyRepo            companyRepo;
  private final CouponUserAuthProvider couponUserAuthProvider;
  
  private final CouponUserRepo  couponUserRepo;
  public Company persistenceAddCompany(Company company) {
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    CouponUser couponUser = CouponUser.builder()
      .username(company.getEmail())
      .password(company.getPassword())
      
      .build();
    this.companyRepo.save(company);
    this.couponUserRepo.save(couponUser);
    this.couponUserAuthProvider.setAuthForCompany(couponUser);
    return company;
    
  }
  
  
  public CouponUser persistenceUpdateCompany(Company company, Company companyFromDb) {
    company.setName(companyFromDb.getName());
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    company.setId(companyFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(companyFromDb.getEmail());
    couponUser.setUsername(company.getEmail());
    couponUser.setPassword(company.getPassword());
    this.companyRepo.save(company);
    return couponUser;
  }
  
  
  public void persistenceDeleteCompany(int id, String email) {
    this.companyRepo.deleteById(id);
    this.couponUserRepo.deleteByUsername(email);
  }
  
  
}
