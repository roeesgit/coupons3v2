package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@CrossOrigin
public class CompanyController {

  private final CompanyServiceImpl companyServiceImpl;
  
  @PostMapping
  public CompanyRespondDto addCompany(@RequestBody @Valid Company company) throws CouponException {
    return this.companyServiceImpl.addCompany(company);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.FOUND)
  public List <Company> getAllCompanies(){
    return this.companyServiceImpl.getAllCompanies();
  }
  
  @PutMapping("/{id}")
  public void updateCompany(@RequestBody Company company, @PathVariable int id) throws CouponException {
    this.companyServiceImpl.updateCompany(company,id);
  }
  
  @DeleteMapping("/{id}")
  public void deleteCompany( @PathVariable int id) throws CouponException {
    this.companyServiceImpl.deleteCompany(id);
  }
  
  
  @GetMapping("/{id}")
  public CompanyRespondDto getSingleCompany(@PathVariable int id) throws CouponException {
    return this.companyServiceImpl.getSingleCompany(id);
  }
}
