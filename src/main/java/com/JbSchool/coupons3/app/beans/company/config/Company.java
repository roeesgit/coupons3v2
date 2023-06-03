/**
 * The {@code Company} class represents a company in the coupon system.
 * It contains information about the company, such as its name, email, and password.
 * The class also includes validation annotations to enforce data integrity.
 */
package com.JbSchool.coupons3.app.beans.company.config;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

/**
 * The {@code Company} class represents a company in the coupon system.
 * It contains information about the company, such as its name, email, and password.
 * The class also includes validation annotations to enforce data integrity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "companies")
@EntityListeners(AuditingEntityListener.class)
public class Company {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(updatable = false, name = "name", nullable = false)
  @Length(min = 3, max = 15, message = "Please provide a valid user name between 3-15 characters")
  private String name;
  
  @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
  @Length(min = 3, max = 25, message = "Please provide a valid email between 3-25 characters")
  @Column(name = "email", nullable = false)
  private String email;
  
  @Length(min = 3, max = 70, message = "Please provide a valid password between 3-70 characters")
  @Pattern(
    regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!.*\\s).{8,}$",
    message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, must not contain spaces, and must be at least 8 characters long"
  )
  @Column(name = "password")
  private String password;
  
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdDate;
  
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
  /**
   * Converts the name and email fields to lowercase before persisting or updating the entity.
   * This ensures case-insensitive uniqueness for the name and email fields.
   */
  @PrePersist
  @PreUpdate
  public void convertFieldsToLowercase() {
    this.name = this.name.toLowerCase();
    this.email = this.email.toLowerCase();
  }
}