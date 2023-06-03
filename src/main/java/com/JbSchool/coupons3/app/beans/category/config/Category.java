/**
 * The {@code Category} class represents a category in the coupon system.
 * It contains the necessary properties and methods to manage category information.
 */
package com.JbSchool.coupons3.app.beans.category.config;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

/**
 * The {@code Category} entity class represents a category in the coupon system.
 * It is mapped to the "categories" table in the database.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "categories")
public class Category {
  /**
   * The unique identifier of the category.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  /**
   * The name of the category.
   */
  @Column(name = "name")
  private String name;
}
