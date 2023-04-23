package com.JbSchool.coupons3.app.beans.company;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.company.facade.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Service @RequiredArgsConstructor @Transactional
public class CompanyServiceImpl implements CompanyService {
  
  
  private final CompanyRepo companyRepo;

  private final CouponService  couponService;
  private final CouponUserRepo couponUserRepo;
  private final TokenConfig    tokenConfig;
  private final CouponAppMapper couponAppMapper;
  private final PasswordEncoder passwordEncoder;
  
  
  @Override @Transactional
  public CompanyRespondDto addCompany(Company company) throws CouponException {
    if (this.companyRepo.existsById(company.getId())) {
        throw new CouponException(CompanyExceptionProvider.COMPANY_ID_ALREADY_EXIST.getMessage());
    }
    Company persistenceCompany = persistenceAdd(company);
    return CompanyRespondDto.builder().name(persistenceCompany.getName()).email(persistenceCompany.getEmail()).build();
  }
  
  
  private Company persistenceAdd(Company company) {
    String encodedPassword = passwordEncoder.encode(company.getPassword());
    company.setPassword(encodedPassword);
    CouponUser couponUser = CouponUser.builder()
      .username(company.getEmail())
      .password(encodedPassword)
      .build();
    this.companyRepo.save(company);
    this.couponUserRepo.save(couponUser);
    return company;

  }
  
  
  @Override @Transactional
  public TokenResponseDTO updateCompany(Company company, int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    
    //todo: apply if spring boot validation failed with update
//    if (this.companyRepo.existsByEmailAndIdNot(company.getEmail(), id)) {
//      throw new CouponException(ErrorMessageProvider.EMAIl_ALREADY_EXIST.getMessage());
//    }
    return new TokenResponseDTO(tokenConfig.generateToken(tokenConfig.buildClaims(
      persistenceUpdate(company,companyFromDb))));
  }
  
  
  private CouponUser persistenceUpdate(Company company,Company companyFromDb) {
    String encodedPassword = passwordEncoder.encode(company.getPassword());
    company.setName(companyFromDb.getName());
    company.setPassword(encodedPassword);
    company.setId(companyFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(companyFromDb.getEmail());
    couponUser.setUsername(company.getEmail());
    couponUser.setPassword(encodedPassword);
    this.companyRepo.save(company);
    return couponUser;
  }
  
  
  @Override
  public CompanyRespondDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    
    return CompanyRespondDto.builder()
      .name(companyFromDb.getName())
      .email(companyFromDb.getEmail())
      .build();
  }
  
  @Override
  public void deleteCompany(int id) throws CouponException {
    if (!this.companyRepo.existsById(id)) {
      throw new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage());
    }
    CompanyRespondDto company = getSingleCompany(id);
    this.companyRepo.deleteById(id);
    this.couponUserRepo.deleteByUsername(company.getEmail());
  }
  
  
  @Override
  public List <Company> getAllCompanies() {
    return this.companyRepo.findAll();
  }
  
  
  
}
