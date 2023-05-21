package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

  private final CompanyServiceImpl companyServiceImpl;
  
  
  @PostMapping
  public CompanyDto addCompany(@RequestBody @Valid Company company) throws CouponException {
    return this.companyServiceImpl.addCompany(company);
  }
  
  @PutMapping("/{companyId}")
  public CompanyDto updateCompany(@RequestBody @Valid Company company, @PathVariable int companyId) throws CouponException {
    return companyServiceImpl.updateCompany(company,companyId);
  }
  
  @DeleteMapping("/{id}")
  public void deleteCompany( @PathVariable int id) throws CouponException {
    this.companyServiceImpl.deleteCompany(id);
  }
  
  @GetMapping
  public List <CompanyDto> getAllCompanies(){
    return this.companyServiceImpl.getAllCompanies();
  }
  
  
  @GetMapping("/{id}")
  public CompanyDto getSingleCompany(@PathVariable int id) throws CouponException {
    return this.companyServiceImpl.getSingleCompany(id);
  }
  @GetMapping("/loggedCompany")
  public CompanyDto getLoggedCompany() throws CouponException {
    return this.companyServiceImpl.getLoggedCompany();
  }
}
