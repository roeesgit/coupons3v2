/**
 * The {@code CategoryRepo} interface represents a repository for managing {@link Category} entities in the coupon system.
 * It extends the {@link JpaRepository} interface from Spring Data JPA.
 */
package com.JbSchool.coupons3.app.beans.category.config;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code CategoryRepo} interface represents a repository for managing {@link Category} entities in the coupon system.
 * It extends the {@link JpaRepository} interface from Spring Data JPA.
 */
public interface CategoryRepo extends JpaRepository<Category, Integer> {
  // Additional custom repository methods can be defined here if needed.
}