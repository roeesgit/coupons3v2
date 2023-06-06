package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.config.*;
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
/*This class make init foe testing*/
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
  
    // inMemoryUser = ADMIN!!!!!
  
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
      .username("admin@admin.com")
      .loggedUserName("Admin")
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
        .name(cp.name().toLowerCase())
        .build();
      categories.add(category);
    }
    categoryRepo.saveAll(categories);
  }
  
  
  @Transactional
  
  private void initCompaniesAndCoupons() {
    int picNum = 0;
    int COMPANIES = 10;
    List <String> companyNames = List.of("Zara", "American Eagle", "pull&bear", "Golf", "Renuar");
    List<String> titles = List.of("2 for 1 shirt", "tops", "jackets", "underwear", "shoes") ;
    List<String> descriptions = List.of("buy 1 get the second for free", "varies women tops 30% off"
      , "for limited time only", "women & men underwear 50% off", "37 and under, anna its for you 😉");
    List <Coupon> coupons = new ArrayList <>();
    int COUPONS = 130;
    for (int i = 0; i < COMPANIES; i++) {
      Company company = new Company();
      company.setName("Company " + (i + 1));
//      company.setName(companyNames.get(i));
      company.setEmail("company" + (i + 1) + "@gmail.com");
      company.setPassword(passwordEncoder.encode("1234"));
      CouponUser companyUser = CouponUser.builder()
        .password(company.getPassword())
        .username(company.getEmail())
        .loggedUserName(company.getName())
          .build();
      couponUserRepo.save(companyUser);
      companyRepo.save(company);
      this.couponUserAuthProvider.setAuthForCompany(companyUser);
      for (int j = 0; j < COUPONS; j++) {
        Coupon coupon = new Coupon();
        int ranDays = random.nextInt(55) + 5;
        coupon.setAmount((random.nextInt(100) + 10)*5);
        coupon.setEndDate(LocalDate.now().plus(4, ChronoUnit.MONTHS).plusDays(ranDays));
        coupon.setCompanyId(company.getId());
        coupon.setCategory(
          CategoryProvider.values()
            [(random.nextInt(CategoryProvider.values().length - 1) + 1)]
            .toString().toLowerCase());
        coupon.setTitle("title " + (j + 1)+ " Company{"+company.getId());
//        coupon.setTitle(titles.get(j));
        coupon.setDescription("This is the coupon "+ (j + 1)+" description");
//        coupon.setDescription(descriptions.get(j));
        coupon.setStartDate(LocalDate.now().plusDays(ranDays));
        picNum++;
        String pics =  "https://picsum.photos/seed/"+picNum+"/150/150";
        coupon.setImage(pics);
        coupon.setPrice((random.nextInt(60) + 2)*5);
//        if (i == 0 && j == 0) {
//          coupon.setCategory(CategoryProvider.values()[0].toString().toLowerCase());
//          coupon.setTitle("TEST " + (j + 1));
//          coupon.setAmount(random.nextInt(3) + 1);
//          coupon.setEndDate(LocalDate.now().minusDays(1));
//        }
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
        .firstName("customer" + (i + 1))
        .lastName("last" + (i + 1))
        .email("customer" + (i + 1) + "@gmail.com")
        .password(this.passwordEncoder.encode("1234"))
        .build();
      CouponUser customerUser = CouponUser.builder()
        .password(customer.getPassword())
        .username(customer.getEmail())
        .loggedUserName(customer.getFirstName())
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
