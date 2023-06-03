package com.JbSchool.coupons3.app.beans.company.facade;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.utils.*;
import jakarta.validation.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 
 The CompanyController class handles the RESTful API endpoints related to company operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/companies")
public class CompanyController {

  private final CompanyServiceImpl companyServiceImpl;
  
  /**
   
   Adds a new company to the system.
   @param company the company to be added
   @return the DTO representation of the added company
   @throws CouponException if an error occurs during the operation
   */
  @PostMapping
  public CompanyDto addCompany(@RequestBody @Valid Company company) throws CouponException {
    return this.companyServiceImpl.addCompany(company);
  }
  /**
   
   Updates an existing company in the system.
   @param company the updated company information
   @param companyId the ID of the company to be updated
   @throws CouponException if an error occurs during the operation
   */
  @PutMapping("/{companyId}")
  public void updateCompany(@RequestBody @Valid Company company, @PathVariable int companyId) throws CouponException {
    companyServiceImpl.updateCompany(company, companyId);
  }
  /**
   
   Deletes a company from the system.
   @param id the ID of the company to be deleted
   @throws CouponException if an error occurs during the operation
   */
  @DeleteMapping("/{id}")
  public void deleteCompany(@PathVariable int id) throws CouponException {
    this.companyServiceImpl.deleteCompany(id);
  }
  /**
   
   Retrieves all companies from the system.
   @return a list of DTO representations of companies
   */
  @GetMapping
  public List<CompanyDto> getAllCompanies() {
    return this.companyServiceImpl.getAllCompanies();
  }
  /**
   
   Retrieves a single company from the system.
   @param id the ID of the company to retrieve
   @return the DTO representation of the retrieved company
   @throws CouponException if the company is not found
   */
  @GetMapping("/{id}")
  public CompanyDto getSingleCompany(@PathVariable int id) throws CouponException {
    return this.companyServiceImpl.getSingleCompany(id);
  }
  /**
   
   Retrieves the logged-in company.
   @return the DTO representation of the logged-in company
   @throws CouponException if the logged-in company is not found
   */
  @GetMapping("/loggedCompany")
  public CompanyDto getLoggedCompany() throws CouponException {
    return this.companyServiceImpl.getLoggedCompany();
  }
}






