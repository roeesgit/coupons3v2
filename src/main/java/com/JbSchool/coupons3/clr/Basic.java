package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.security.config.*;
import com.JbSchool.coupons3.security.entites.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
@Component
@RequiredArgsConstructor
public class Basic implements CommandLineRunner {
  
  private final CouponUserAuthProvider couponUserAuthProvider;
  private final CouponUserRepo         couponUserRepo;
  private final CouponAuthRepo         couponAuthRepo;
  private final PasswordEncoder        passwordEncoder;
  private final CategoryRepo    categoryRepo;
  private final CompanyRepo     companyRepo;
  private final CouponRepo      couponRepo;
  private final CustomerRepo    customerRepo;
  private final PurchaseRepo    purchaseRepo;
  
  private final Random random = new Random();
  
  
  @Override
  public void run(String... args) {
//    initAdminOnly();
//    initAll();

  
  }
  
  
  private void initAll() {
  
  initAdminOnly();
    initCategories();
    System.out.println("Categories Done");
    initCompaniesAndCoupons();
    System.out.println("Companies and coupons Done");
    initCustomers();
    System.out.println("Customers Done");
    initPurchases();
    System.out.println("Purchases Done");
  
    System.out.println("init done!");
  
  
  }
  
  
  private void initAdminOnly() {
    initCouponUsersAuth();
    CouponUser admin = CouponUser.builder()
      .password(passwordEncoder.encode("admin"))
      .username("admin")
      .build();
  
    this.couponUserRepo.save(admin);
    this.couponUserAuthProvider.setAuthForAdmin(admin);
  
  }
  
  
  private void initCouponUsersAuth() {
    for (CouponAuthorization ca : CouponAuthorization.values()) {
      CouponAuth couponAuth = CouponAuth.builder()
        .couponAuthorization(ca)
        .build();
      this.couponAuthRepo.save(couponAuth);
    }
  }
  
  
  private void initCategories() {
    List <Category> categories = new ArrayList <>();
    for (CategoryProvider cp : CategoryProvider.values()) {
      Category category = Category.builder()
        .name(cp)
        .build();
      categories.add(category);
    }
    categoryRepo.saveAll(categories);
  }
  
  
  @Transactional
  
  private void initCompaniesAndCoupons() {
    int picNum = 0;
    int COMPANIES = 10;
    List <Coupon> coupons = new ArrayList <>();
    int COUPONS = 50;
    for (int i = 0; i < COMPANIES; i++) {
      Company company = new Company();
      company.setName("Company name " + (i + 1));
      company.setEmail("email" + (i + 1) + "gmail.com");
      company.setPassword(passwordEncoder.encode("12345"));
      if (i == 0) {
        company.setName("TEST");
        company.setEmail("aaaaa");
        company.setPassword(passwordEncoder.encode("aaaaa"));
      }
      CouponUser companyUser = CouponUser.builder()
        .password(company.getPassword())
        .username(company.getEmail())
        .build();
      couponUserRepo.save(companyUser);
      companyRepo.save(company);
      this.couponUserAuthProvider.setAuthForCompany(companyUser);
      for (int j = 0; j < COUPONS; j++) {
        Coupon coupon = new Coupon();
        int ranDays = random.nextInt(55) + 5;
        coupon.setAmount(random.nextInt(80) + 20);
        coupon.setEndDate(LocalDate.now().plus(4, ChronoUnit.MONTHS).plusDays(ranDays));
        coupon.setCompanyId(company.getId());
        coupon.setCategory(CategoryProvider.values()[(random.nextInt(CategoryProvider.values().length - 1) + 1)]);
        coupon.setTitle("Company{"+company.getId()+ "} title " + (j + 1));
        coupon.setDescription("description " + (j + 1));
        coupon.setStartDate(LocalDate.now().plusDays(ranDays));
        picNum++;
        String pics =  "https://picsum.photos/seed/"+picNum+"/150/150";
        coupon.setImage(pics);
        coupon.setPrice(random.nextInt(1945) + 55);
        if (i == 0 && j == 0) {
          coupon.setCategory(CategoryProvider.values()[0]);
          coupon.setTitle("TEST " + (j + 1));
          coupon.setAmount(random.nextInt(3) + 1);
          coupon.setEndDate(LocalDate.now().minusDays(1));
        }
        coupons.add(coupon);
      }
    }
//    this.couponUserRepo.saveAll(companiesUsers);
    this.couponRepo.saveAll(coupons);
//    this.companyRepo.saveAll(companies);
  }
  
  
  private void initCustomers() {
    int CUSTOMERS = 200;
    for (int i = 0; i < CUSTOMERS; i++) {
      Customer customer = Customer.builder()
        .firstName("first name " + (i + 1))
        .lastName("last name " + (i + 1))
        .email("email" + (i + 1) + "@gmail.com")
        .password(this.passwordEncoder.encode("12345"))
        .build();
      if (i == 0) {
        customer.setFirstName("TEST");
        customer.setLastName("Testy");
        customer.setEmail("zzzzz");
        customer.setPassword(this.passwordEncoder.encode("zzzzz"));
      }
      
      CouponUser customerUser = CouponUser.builder()
        .password(customer.getPassword())
        .username(customer.getEmail())
        .build();
      this.customerRepo.save(customer);
      this.couponUserRepo.save(customerUser);
      this.couponUserAuthProvider.setAuthForCustomer(customerUser);
  
    }
  }
  
  
  private void initPurchases() {
    List <Coupon> coupons = this.couponRepo.findAll();
    List <Customer> customers = this.customerRepo.findAll();
    List <Purchase> purchases = new ArrayList <>();
    int PURCHASES = 1000;
    for (int i = 0; i < PURCHASES; i++) {
      Purchase purchase = new Purchase();
      purchase.setCouponId(coupons.get(random.nextInt(coupons.size())).getId());
      purchase.setCustomerId(customers.get(random.nextInt(customers.size())).getId());
      purchases.add(purchase);
    }
    this.purchaseRepo.saveAll(purchases);
  }
  
  
}
