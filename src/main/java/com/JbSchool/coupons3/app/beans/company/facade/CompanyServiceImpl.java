package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {
  
  
  private final CompanyRepo        companyRepo;
  private final CouponExcValidator couponExcValidator;
  
  private final PersistenceCouponUser persistenceCouponUser;
  private final Mapper                mapper;
  
  //todo  check @transactional at class level
  
  
  @Override
  @Transactional
  public UserDto addCompany(Company company) {
    Company persistenceCompany = this.persistenceCouponUser.addCompany(company);
    return this.mapper.companyToUserDto(persistenceCompany);
  }
  
  
  @Override
//  @Transactional
  public void updateCompany(Company company, int companyId) throws CouponException {
    //todo לבדוק אם אפשר לקצר 3 קריאות לDB ע"י QUERY
    Company companyFromDb =  this.couponExcValidator.getOptionalCompany(companyId);
    this.mapper.companyToUserDto(
      this.persistenceCouponUser.updateCompany(company, companyFromDb));
  }
  
  
  @Override
  public void deleteCompany(int id) throws CouponException {
    Company company = this.couponExcValidator.getOptionalCompany(id);
    this.persistenceCouponUser.deleteCompany(id, company.getEmail());
  }
  
  
  @Override
  public UserDto getSingleCompany(int id) throws CouponException {
    Company companyFromDb = this.companyRepo.findById(id).orElseThrow(() -> new CouponException(ErrorMessageProvider.ID_NOT_FOUND.getMessage()));
    return this.mapper.companyToUserDto(companyFromDb);
  }
  
  
  @Override
  public List <UserDto> getAllCompanies() {
    return this.mapper.companyListToUserDtoList(this.companyRepo.findAll());
  }
  
  
  @Override
  public UserDto getLoggedCompany() throws CouponException {
    Company company = this.couponExcValidator.getOptionalCompany(
      this.mapper.userIdFromSCH()
    );
    return this.mapper.companyToUserDto(company);
  }
  
  
}
