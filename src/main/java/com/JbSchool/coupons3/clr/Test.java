package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.coupon.*;
import lombok.*;
import org.springframework.boot.*;
//@Component
@RequiredArgsConstructor
public class Test implements CommandLineRunner {
  
  private  final Pagination      pagination;
  private  final CategoryService categoryService;
  @Override
  public void run(String... args) {
           System.out.println("********* Test 1 **********");
    pagination.hh();
           System.out.println("********* Test 2 **********");
    System.out.println(categoryService.getAllCategories());

  }
  
  
}
