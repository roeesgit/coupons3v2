package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.coupon.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
@Component
@RequiredArgsConstructor
public class Test implements CommandLineRunner {
  
  private  final Pagination      pagination;
  private  final CategoryService    categoryService;
  
  @Autowired
  @Qualifier("companyServiceImpl")
  private  CompanyService companyService;
  
  @Override
  public void run(String... args) {
           System.out.println("********* CLR 1 **********");
//    pagination.hh();
    companyService.getAllCompanies().forEach(System.out::println);
           System.out.println("********* CLR 2 **********");
    System.out.println(categoryService.getAllCategories());

  }
  
  
}
