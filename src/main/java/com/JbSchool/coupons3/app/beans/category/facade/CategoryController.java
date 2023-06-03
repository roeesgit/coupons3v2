package com.JbSchool.coupons3.app.beans.category.facade;

import com.JbSchool.coupons3.app.beans.category.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  
  private final CategoryService categoryService;
  
  @GetMapping
  public List <Category> getAllCategories() {
    return this.categoryService.getAllCategories();
  }
}
