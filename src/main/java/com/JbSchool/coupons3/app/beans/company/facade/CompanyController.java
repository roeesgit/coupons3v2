package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.dto.*;
import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
@CrossOrigin

public class CompanyController {

  private final CompanyServiceImpl companyServiceImpl;
  
  @PostMapping
  public UserDto addCompany(@RequestBody @Valid Company company) throws CouponException {
    return this.companyServiceImpl.addCompany(company);
  }
  
  @GetMapping
  @ResponseStatus(HttpStatus.FOUND)
  public List <Company> getAllCompanies(){
    return this.companyServiceImpl.getAllCompanies();
  }
  
  @PutMapping("/{id}")
  public TokenDTO updateCompany(@RequestBody @Valid Company company, @PathVariable int id) throws CouponException {
    return companyServiceImpl.updateCompany(company);
  }
  
  @DeleteMapping("/{id}")
  public void deleteCompany( @PathVariable int id) throws CouponException {
    this.companyServiceImpl.deleteCompany(id);
  }
  
  
  @GetMapping("/{id}")
  public UserDto getSingleCompany(@PathVariable int id) throws CouponException {
    return this.companyServiceImpl.getSingleCompany(id);
  }
}
