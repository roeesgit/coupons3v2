package com.JbSchool.coupons3.app.beans.coupon.config;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.*;
import org.springframework.context.annotation.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import java.time.*;
/**
 
 The Coupon class represents a coupon entity in the system.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "coupons")
@EntityListeners(AuditingEntityListener.class)
public class Coupon {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  private int id;
  
  
  @Column(name = "category", nullable = false/* , updatable = false*/)
  private String category;
  
  @Length(min = 3, max = 20, message = "Please provide a valid title between 3-20 char")
  @Column(name = "title", nullable = false)
  private String title;
  
  @Length(min = 15, max = 100, message = "Please provide a valid user name between 15-100 char")
  @Column(name = "description", nullable = false)
  private String description;
  
//  @FutureOrPresent(message = "date must be after today")
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Min(value = 100,message = "Minimum amount is 100")
  @Max(value = 1000,message = "Maximum amount is 1000")
  @Column(name = "amount", nullable = false)
  private int amount;
  
  @Min(value = 3,message = "Minimum amount is 100")
  @Max(value = 2000,message = "Maximum amount is 2000")
  @Column(name = "price", nullable = false)
  private double price;
  
  @Pattern(regexp = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$" ,message = "Must be a valid url")
  @Column(name = "image", nullable = false)
  private String image;
  
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
  @Column(name = "company_id")
  private int companyId;
  /**
   
   Converts fields to lowercase before persisting or updating the entity.
   */
  @PrePersist
  @PreUpdate
  public void convertFieldsToLowercase() {
    this.image = this.image.toLowerCase();
    this.description = this.description.toLowerCase();
  }
}
