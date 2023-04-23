package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.security.entites.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.time.temporal.*;
import java.util.*;
@Component
@RequiredArgsConstructor
@Transactional
public class Basic implements CommandLineRunner {
  private final CouponUserRepo  couponUserRepo;
  private final CouponAuthRepo  couponAuthRepo;
  private final  PasswordEncoder passwordEncoder;
  private final CategoryRepo  categoryRepo;
  private final CompanyRepo  companyRepo;
  private final CouponRepo   couponRepo;
  private final CustomerRepo customerRepo;
  private final PurchaseRepo purchaseRepo;
  
  private final Random random = new Random();
  
  
  @Override
  public void run(String... args){
    Pageable limit = PageRequest.of(0, 1);
    Page <Company> companies = this.companyRepo.findAll(limit);
    
    if (companies.get().findAny().isEmpty()) {
      List <CouponAuth> couponAuths = initCouponUsersAuth();
      CouponUser admin = CouponUser.builder()
        .password(passwordEncoder.encode("admin"))
        .username("admin")
        .couponAuths(List.of(couponAuths.get(0)))
          .build();
      this.couponUserRepo.save(admin);
      
      initCategories();
      initCompaniesAndCoupons(couponAuths);
      initCustomers(couponAuths);
      initPurchases();
      
      System.out.println("init done!");
      
    }
  }
  
  
  private List<CouponAuth> initCouponUsersAuth() {
    Set <CouponAuth> couponAuths = new HashSet <>();
    for (CouponAuthorization ca : CouponAuthorization.values()) {
      CouponAuth couponAuth = CouponAuth.builder()
        .couponAuthorization(ca)
        .build();
      couponAuths.add(couponAuth);
    }
    return this.couponAuthRepo.saveAll(couponAuths);
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
  
  private void initCompaniesAndCoupons(List <CouponAuth> couponAuths) {
    List <Company> companies = new ArrayList <>();
    List <CouponUser> companiesUsers = new ArrayList <>();
    int COMPANIES = 10;
    List <Coupon> coupons = new ArrayList <>();
    int COUPONS = 50;
    for (int i = 0; i < COMPANIES; i++) {
      Company company = new Company();
      String encodedPassword = passwordEncoder.encode("12345");
      company.setName("Company name " + (i + 1));
      company.setEmail("email(" + (i + 1) + ")@gmail.com");
      company.setPassword(encodedPassword);
      if (i == 0) {
        company.setName("TEST");
        company.setEmail("aaaaa");
        company.setPassword(passwordEncoder.encode("aaaaa"));
      }
      CouponUser companyUser = CouponUser.builder()
        .password(company.getPassword())
        .username(company.getEmail())
        .couponAuths(List.of(couponAuths.get(2)))
          .build();
      companiesUsers.add(companyUser);
      companies.add(company);
      for (int j = 0; j < COUPONS; j++) {
        Coupon coupon = new Coupon();
        int ranDays = random.nextInt(55) + 5;
        coupon.setAmount(random.nextInt(80) + 20);
        coupon.setEndDate(LocalDate.now().plus(4, ChronoUnit.MONTHS).plusDays(ranDays));
        coupon.setCompany(Company.builder().id(i+1).build());
        coupon.setCategory(CategoryProvider.values()[(random.nextInt(CategoryProvider.values().length-1) + 1)]);
        coupon.setTitle("title " + (j + 1));
        coupon.setDescription("description " + (j + 1));
        coupon.setStartDate(LocalDate.now().plusDays(ranDays));
        coupon.setImage("image " + (j + 1));
        coupon.setPrice(random.nextInt(1945) + 55);
        if (i == 0&&j==0) {
          coupon.setCategory(CategoryProvider.values()[0]);
          coupon.setTitle("TEST " + (j + 1));
          coupon.setAmount(random.nextInt(3) + 1);
          coupon.setEndDate(LocalDate.now().minusDays(1));
        }
        coupons.add(coupon);
      }
    }
    this.couponUserRepo.saveAll(companiesUsers);
    this.couponRepo.saveAll(coupons);
    this.companyRepo.saveAll(companies);
  }
  
  
  private void initCustomers(List <CouponAuth> couponAuths) {
    List <Customer> customers = new ArrayList <>();
    List <CouponUser> customersUsers = new ArrayList <>();
    int CUSTOMERS = 200;
    for (int i = 0; i < CUSTOMERS; i++) {
      Customer customer = new Customer();
      String encodedPassword = this.passwordEncoder.encode("12345");
        customer.setFirstName("first name " + (i + 1));
      customer.setLastName("last name " + (i + 1));
      customer.setEmail("email(" + (i + 1) + ")@gmail.com");
      customer.setPassword(encodedPassword);
      if (i == 0) {
        customer.setFirstName("TEST");
        customer.setLastName("Testy");
        customer.setEmail("zzzzz");
        customer.setPassword(this.passwordEncoder.encode("zzzzz"));
      }
      
      CouponUser customerUser = CouponUser.builder()
        .password(customer.getPassword())
        .username(customer.getEmail())
        .couponAuths(List.of(couponAuths.get(1)))
        .build();
      customersUsers.add(customerUser);
      customers.add(customer);
    }
      this.couponUserRepo.saveAll(customersUsers);
      this.customerRepo.saveAll(customers);
  }
  
  
  private void initPurchases() {
    List <Coupon> coupons = this.couponRepo.findAll();
    List <Customer> customers = this.customerRepo.findAll();
    List <Purchase> purchases = new ArrayList <>();
    int PURCHASES = 1000;
    for (int i = 0; i < PURCHASES; i++) {
      Purchase purchase = new Purchase();
      purchase.setCoupon(coupons.get(random.nextInt(coupons.size())));
      purchase.setCustomer(customers.get(random.nextInt(customers.size())));
      purchases.add(purchase);
    }
    this.purchaseRepo.saveAll(purchases);
  }
  
  
}
