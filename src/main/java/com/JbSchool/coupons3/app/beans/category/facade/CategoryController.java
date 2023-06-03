/**
 * The {@code CategoryController} class is a REST controller that handles HTTP requests related to categories in the coupon system.
 * It defines API endpoints for managing categories.
 */
package com.JbSchool.coupons3.app.beans.category.facade;

import com.JbSchool.coupons3.app.beans.category.config.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The {@code CategoryController} class is a REST controller that handles HTTP requests related to categories in the coupon system.
 * It defines API endpoints for managing categories.
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
  
  private final CategoryService categoryService;
  
  /**
   * Retrieves all categories from the coupon system.
   *
   * @return a list of all categories
   */
  @GetMapping
  public List<Category> getAllCategories() {
    return this.categoryService.getAllCategories();
  }
}
