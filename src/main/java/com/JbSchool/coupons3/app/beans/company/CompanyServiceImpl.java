package com.JbSchool.coupons3.app.beans.company;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
  
  
  private final CompanyRepo companyRepo;
  
  private final CouponUserRepo  couponUserRepo;
  private final TokenConfig     tokenConfig;
  private final PasswordEncoder        passwordEncoder;
  private final PersistenceCouponUser        persistenceCouponUser;
  
  
  @Override
  @Transactional
  public UserDto addCompany(Company company) throws CouponException {
    if (this.companyRepo.existsById(company.getId())) {
      throw new CouponException(CompanyExceptionProvider.COMPANY_ID_ALREADY_EXIST.getMessage());
    }
    Company persistenceCompany = this.persistenceCouponUser.persistenceAddCompany(company);
    return UserDto.builder()
      .name(persistenceCompany.getName())
      .email(persistenceCompany.getEmail())
      .build();
  }
  
  
 
  
  
  @Override
  @Transactional
  public TokenResponseDTO updateCompany(Company company, int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id)
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    
    return new TokenResponseDTO(
      tokenConfig.generateToken(
        tokenConfig.buildClaims(
     this.persistenceCouponUser.persistenceUpdateCompany(
        company, companyFromDb)
        )));
  }
  

  
  @Override
  public UserDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    
    return UserDto.builder()
      .name(companyFromDb.getName())
      .email(companyFromDb.getEmail())
      .build();
  }
  
  
  @Override
  public void deleteCompany(int id) throws CouponException {
    if (!this.companyRepo.existsById(id)) {
      throw new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage());
    }
    UserDto company = getSingleCompany(id);
    this.persistenceCouponUser.persistenceDeleteCompany(id, company.getEmail());
  }
  
  
  @Override
  public List <Company> getAllCompanies() {
    return this.companyRepo.findAll();
  }
  
  
  
}
