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
@Component
@RequiredArgsConstructor
public class PersistenceCouponUser {
  
  private final PasswordEncoder        passwordEncoder;
  private final CompanyRepo            companyRepo;
  private final CustomerRepo customerRepo;
  private final CouponRepo   couponRepo;
  private final PurchaseRepo purchaseRepo;
  private final CouponUserAuthProvider couponUserAuthProvider;
//  @PersistenceContext
//  private final EntityManager          entityManager;
  
  private final CouponUserRepo couponUserRepo;
  
  
  //  @Transactional
  public Company addCompany(Company company) {
   //todo entityManager.getTransaction().begin();
//    try {
      
      company.setPassword(passwordEncoder.encode(company.getPassword()));
      CouponUser couponUser = CouponUser.builder()
        .username(company.getEmail())
        .password(company.getPassword()).build();
      this.companyRepo.save(company);
      this.couponUserRepo.save(couponUser);
      this.couponUserAuthProvider.setAuthForCompany(couponUser);

      
      
      //      entityManager.getTransaction().commit();
//      System.out.println(entityManager.getTransaction().isActive());
//    } catch (Exception e) {
//      entityManager.getTransaction().rollback();
//    }
   
    return company;
    
  }
  
  
  @Transactional
  public Company updateCompany(Company company, Company companyFromDb) {
    
    company.setPassword(passwordEncoder.encode(company.getPassword()));
    company.setId(companyFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(companyFromDb.getEmail());
    couponUser.setUsername(company.getEmail());
    couponUser.setPassword(company.getPassword());
    this.companyRepo.save(company);
    return company;
  }
  
  @Transactional
  public void deleteCompany(int id, String email) {
    this.couponRepo.deleteCompanyCouponsPurchases(id);
    this.companyRepo.deleteById(id);
    this.couponUserRepo.deleteByUsername(email);
    int couponsDeleted =  this.couponRepo.deleteByCompanyId(id);
  
    System.out.println("couponsDeleted "+couponsDeleted);
    System.out.println("purchaseDeleted");
  }
  
  
  @Transactional
  public Customer addCustomer(Customer customer) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    CouponUser couponUser = CouponUser.builder()
      .username(customer.getEmail())
      .password(customer.getPassword())
      
      .build();
    this.customerRepo.save(customer);
    this.couponUserRepo.save(couponUser);
    this.couponUserAuthProvider.setAuthForCustomer(couponUser);
    return customer;
    
  }
  
  
  public Customer updateCustomer(Customer customer, Customer customerFromDb) {
    customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    customer.setId(customerFromDb.getId());
    CouponUser couponUser = couponUserRepo.findByUsername(customerFromDb.getEmail());
    couponUser.setUsername(customer.getEmail());
    couponUser.setPassword(customer.getPassword());
    this.couponUserRepo.save(couponUser);
    this.customerRepo.save(customer);
    return customer;
  }
  
  @Transactional
  public void deleteCustomer(String username, int customerId) {
    this.customerRepo.deleteById(customerId);
    this.couponUserRepo.deleteByUsername(username);
    int purchaseDeleted = this.purchaseRepo.deleteByCustomerId(customerId);
    System.out.println("purchaseDeleted "+purchaseDeleted);
  }
  
  
}
