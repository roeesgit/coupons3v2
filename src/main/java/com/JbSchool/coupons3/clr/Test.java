package com.JbSchool.coupons3.clr;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.facade.*;
import lombok.*;
import org.springframework.boot.*;
import org.springframework.stereotype.*;
//@Component
@RequiredArgsConstructor
public class Test implements CommandLineRunner {
  
  private  final Pagination      pagination;
  private  final CategoryService categoryService;
  @Override
  public void run(String... args) throws Exception {
           System.out.println("********* Test 1 **********");
    pagination.hh();
           System.out.println("********* Test 2 **********");
    System.out.println(categoryService.getAllCategories());

  }
  
  
}
