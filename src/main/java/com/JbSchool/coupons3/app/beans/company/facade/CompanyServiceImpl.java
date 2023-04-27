package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.dto.*;
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
  
  private final PersistenceCouponUser        persistenceCouponUser;
  private final Mapper        mapper;
  
  
  @Override
  @Transactional
  public UserDto addCompany(Company company) throws CouponException {
    Company persistenceCompany = this.persistenceCouponUser.persistenceAddCompany(company);
    return this.mapper.companyToUserDto(persistenceCompany);
  }
  
  
 
  
  
  @Override
  @Transactional
  public TokenDTO updateCompany(Company company) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(this.mapper.companyIdFromSCH())
      .orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    CouponUser persistenceCompany = this.persistenceCouponUser.persistenceUpdateCompany(company,companyFromDb);
  
    return this.mapper.companyToTokenDTO(persistenceCompany);
  }
  

  
  @Override
  public UserDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    return this.mapper.companyToUserDto(companyFromDb);
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
