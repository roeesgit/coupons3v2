package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.facade.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
@Component

public class Test implements CommandLineRunner {
  
  @Autowired
  private   Pagination      pagination;
  @Autowired
  private   CategoryService categoryService;
  @Autowired
  @Qualifier("companyServiceImpl")
  private  CompanyService companyService;
  
  @Override
  public void run(String... args) {
           System.out.println("********* CLR start **********");
     // TODO: 11/22/2022 test
           System.out.println("********* pagination **********");
    pagination.hh();
//    companyService.getAllCompanies().forEach(System.out::println);
//    System.out.println(categoryService.getAllCategories());
    System.out.println("********* CLR end **********");

  }
  
  
}
