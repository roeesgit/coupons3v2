/**
 * The {@code CategoryService} class is a service class that provides operations for managing categories in the coupon system.
 * It interacts with the {@link CategoryRepo} to perform CRUD operations on categories.
 */
package com.JbSchool.coupons3.app.beans.category.facade;

import com.JbSchool.coupons3.app.beans.category.config.Category;
import com.JbSchool.coupons3.app.beans.category.config.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The {@code CategoryService} class is a service class that provides operations for managing categories in the coupon system.
 * It interacts with the {@link CategoryRepo} to perform CRUD operations on categories.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryService {
  
  private final CategoryRepo categoryRepo;
  
  /**
   * Retrieves all categories from the coupon system.
   *
   * @return a list of all categories
   */
  @GetMapping
  public List<Category> getAllCategories() {
    return this.categoryRepo.findAll();
  }
  
  // Additional service methods can be added here if needed.
}