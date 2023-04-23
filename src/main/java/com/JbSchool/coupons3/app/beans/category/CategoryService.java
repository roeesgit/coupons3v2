package com.JbSchool.coupons3.app.beans.category;

import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RequiredArgsConstructor
@RestController @RequestMapping("/api/categories")
public class CategoryService {
  
  private final CategoryRepo categoryRepo;
  @GetMapping
  public List<Category> getAllCategories(){
    return this.categoryRepo.findAll();
  }
  
}
