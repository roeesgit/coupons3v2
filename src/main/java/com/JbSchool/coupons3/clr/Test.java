package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.facade.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.data.domain.*;
//@Component
public class Test implements CommandLineRunner {
  
  @Autowired
  private   Pagination      pagination;
//  @Autowired
//  private   CategoryService categoryService;
//  @Autowired
//  @Qualifier("companyServiceImpl")
//  private  CompanyService companyService;
  @Autowired
  private  CompanyRepo     companyRepo;
  
  @Override
  public void run(String... args) {
           System.out.println("********* CLR start **********");
     // TODO: 11/22/2022 test
           System.out.println("********* pagination **********");
    pagination.hh();
    Pageable limit = PageRequest.of(0, 1);
    Page <Company> companies = this.companyRepo.findAll(limit);

//    companyService.getAllCompanies().forEach(System.out::println);
//    System.out.println(categoryService.getAllCategories());
    System.out.println("********* CLR end **********");

  }
  
  
}
