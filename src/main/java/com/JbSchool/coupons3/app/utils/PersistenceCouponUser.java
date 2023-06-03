package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.security.config.*;
import com.JbSchool.coupons3.security.entites.users.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

/**
 * Utility class for persistence operations related to CouponUser entities (Company and Customer).
 */
@Component
@RequiredArgsConstructor
public class PersistenceCouponUser {
  
  private final PasswordEncoder        passwordEncoder;
  private final CompanyRepo            companyRepo;
  private final CustomerRepo customerRepo;
  private final CouponRepo   couponRepo;
  private final PurchaseRepo           purchaseRepo;
  private final CouponUserAuthProvider couponUserAuthProvider;
  
  private final EntityManager          entityManager;
  
  private final CouponUserRepo couponUserRepo;
  
  /**
   * Adds a Company and its associated CouponUser to the database.
   *
   * @param company the Company to be added
   * @return the added Company
   */
    @Transactional
  public Company addCompany(Company company) {
      company.setPassword(passwordEncoder.encode(company.getPassword()));
      CouponUser couponUser = CouponUser.builder()
        .username(company.getEmail())
        .password(company.getPassword())
        .loggedUserName(company.getName())
        .build();
      this.companyRepo.save(company);
      this.couponUserRepo.save(couponUser);
      this.couponUserAuthProvider.setAuthForCompany(couponUser);
    return company;
    
  }
  
  /**
   * Updates a Company and its associated CouponUser in the database.
   *
   * @param company        the updated Company object
   * @param companyFromDb  the Company object from the database
   */
  @Transactional
  public void updateCompany(Company company, Company companyFromDb) {
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    company.setId(companyFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(companyFromDb.getEmail());
    couponUser.setUsername(company.getEmail());
    couponUser.setPassword(company.getPassword());
    this.companyRepo.save(company);
  }
  /**
   * Deletes a Company and its associated data from the database.
   *
   * @param id    the ID of the Company to be deleted
   * @param email the email address of the Company to be deleted
   */
  @Transactional
  public void deleteCompany(int id, String email) {
    this.couponRepo.deleteCompanyCouponsPurchases(id);
    this.companyRepo.deleteById(id);
    this.couponUserRepo.deleteByUsername(email);
    int couponsDeleted =  this.couponRepo.deleteByCompanyId(id);
  }
  
  /**
   * Adds a Customer and its associated CouponUser to the database.
   *
   * @param customer the Customer to be added
   * @return the added Customer
   */
  @Transactional
  public Customer addCustomer(Customer customer) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    CouponUser couponUser = CouponUser.builder()
      .username(customer.getEmail())
      .password(customer.getPassword())
      .loggedUserName(customer.getFirstName())
  
      .build();
    this.customerRepo.save(customer);
    this.couponUserRepo.save(couponUser);
    this.couponUserAuthProvider.setAuthForCustomer(couponUser);
    return customer;
    
  }
  
  /**
   * Updates a Customer and its associated CouponUser in the database.
   *
   * @param customer       the updated Customer object
   * @param customerFromDb the Customer object from the database
   * @return the updated Customer
   */
  public Customer updateCustomer(Customer customer, Customer customerFromDb) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    customer.setId(customerFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(customerFromDb.getEmail());
    couponUser.setUsername(customer.getEmail());
    couponUser.setPassword(customer.getPassword());
    couponUser.setLoggedUserName(customer.getFirstName());
    this.couponUserRepo.save(couponUser);
    this.customerRepo.save(customer);
    return customer;
  }
  /**
   * Deletes a Customer and its associated data from the database.
   *
   * @param username    the username of the Customer to be deleted
   * @param customerId  the ID of the Customer to be deleted
   */
  @Transactional
  public void deleteCustomer(String username, int customerId) {
    this.customerRepo.deleteById(customerId);
    this.couponUserRepo.deleteByUsername(username);
    int purchaseDeleted = this.purchaseRepo.deleteByCustomerId(customerId);
    System.out.println("purchaseDeleted "+purchaseDeleted);
  }
  
  
}
