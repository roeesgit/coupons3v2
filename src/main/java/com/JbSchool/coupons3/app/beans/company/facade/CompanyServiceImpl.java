/**
 
 The CompanyServiceImpl class implements the CompanyService interface and provides
 the functionality to manage company operations.
 */
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
  
  /**
   
   Adds a new company to the system.
   @param company the company to be added
   @return the DTO representation of the added company
   @throws CouponException if an error occurs during the operation
   */
  @Override
  public CompanyDto addCompany(Company company) throws CouponException {
    System.out.println(company);
    this.companyValidator.addCompany(company);
    Company persistenceCompany = this.persistenceCouponUser.addCompany(company);
    return this.mapper.companyToCompanyDto(persistenceCompany);
  }
  /**
   
   Updates an existing company in the system.
   @param company the updated company information
   @param companyId the ID of the company to be updated
   @throws CouponException if an error occurs during the operation
   */
  @Override
  public void updateCompany(Company company, int companyId) throws CouponException {
    this.companyValidator.updateCompany(company, companyId);
    Company companyFromDb = this.companyValidator.getOptionalCompany(companyId);
    this.persistenceCouponUser.updateCompany(company, companyFromDb);
  }
  /**
   
   Deletes a company from the system.
   @param id the ID of the company to be deleted
   @throws CouponException if an error occurs during the operation
   */
  @Override
  @Transactional
  public void deleteCompany(int id) throws CouponException {
    Company company = this.companyValidator.getOptionalCompany(id);
    this.persistenceCouponUser.deleteCompany(id, company.getEmail());
  }
  /**
   
   Retrieves a single company from the system.
   @param id the ID of the company to retrieve
   @return the DTO representation of the retrieved company
   @throws CouponException if the company is not found
   */
  @Override
  public CompanyDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyValidator.getOptionalCompany(id);
    return this.mapper.companyToCompanyDto(companyFromDb);
  }
  /**
   
   Retrieves all companies from the system.
   @return a list of DTO representations of companies
   */
  @Override
  public List<CompanyDto> getAllCompanies() {
    return this.mapper.companyListToUserDtoList(this.companyRepo.findAll());
  }
  /**
   
   Retrieves the logged-in company.
   @return the DTO representation of the logged-in company
   @throws CouponException if the logged-in company is not found
   */
  @Override
  public CompanyDto getLoggedCompany() throws CouponException {
    Company company = this.companyValidator.getOptionalCompany(this.mapper.userIdFromSCH());
    return this.mapper.companyToCompanyDto(company);
  }
  /**
   
   Finds a company by email.
   @param email the email of the company to find
   @return the DTO representation of the found company, or null if not found
   */
  @Override
  public CompanyDto findByEmail(String email) {
    return this.mapper.companyToCompanyDto(this.companyRepo.findByEmail(email));
  }
}