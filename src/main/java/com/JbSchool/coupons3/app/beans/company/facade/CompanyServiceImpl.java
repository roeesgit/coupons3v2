package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
  
  
  private final CompanyRepo      companyRepo;
  private final CompanyValidator companyValidator;
  
  private final PersistenceCouponUser persistenceCouponUser;
  private final Mapper                mapper;
  
  //todo  check @transactional at class level
  
  
  @Override
//  @Transactional
  public CompanyDto addCompany(Company company) throws CouponException {
    System.out.println(company);
    this.companyValidator.addCompany(company);
    Company persistenceCompany = this.persistenceCouponUser.addCompany(company);
    return this.mapper.companyToCompanyDto(persistenceCompany);
  }
  
  
  @Override
//  @Transactional
  public void updateCompany(Company company, int companyId) throws CouponException {
    //todo לבדוק אם אפשר לקצר 3 קריאות לDB ע"י QUERY
    this.companyValidator.updateCompany(company,companyId);
    Company companyFromDb =  this.companyValidator.getOptionalCompany(companyId);
    
     /*companyFromDb = */this.persistenceCouponUser.updateCompany(company, companyFromDb);
//      return this.mapper.companyToCompanyDto(companyFromDb);
  }
  
  
  @Override
  @Transactional
  public void deleteCompany(int id) throws CouponException {
    Company company = this.companyValidator.getOptionalCompany(id);
    this.persistenceCouponUser.deleteCompany(id, company.getEmail());
  }
  
  
  @Override
  public CompanyDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyValidator.getOptionalCompany(id);
    return this.mapper.companyToCompanyDto(companyFromDb);
  }
  
  
  @Override
  public List <CompanyDto> getAllCompanies() {
    return this.mapper.companyListToUserDtoList(this.companyRepo.findAll());
  }
  
  
  @Override
  public CompanyDto getLoggedCompany() throws CouponException {
    Company company = this.companyValidator.getOptionalCompany(
      this.mapper.userIdFromSCH()
    );
    return this.mapper.companyToCompanyDto(company);
  }
  
  
  @Override
  public CompanyDto findByEmail(String email) {
    return this.mapper.companyToCompanyDto(this.companyRepo.findByEmail(email));
  }
  
  
}
