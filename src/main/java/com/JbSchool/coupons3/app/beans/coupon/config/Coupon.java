package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.*;
import com.JbSchool.coupons3.app.beans.company.config.*;
import com.JbSchool.coupons3.app.beans.purchase.config.*;
import com.JbSchool.coupons3.entity_validator.*;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.*;
import org.springframework.context.annotation.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;

import java.time.*;
import java.util.*;

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
  @EntityUniqueFieldConfig(tableName = "coupons",columnName = "id" , message = "Id already exist")
  private int id;
  
  
  @Column(name = "category", nullable = false , updatable = false)
  private String category;
  
  @Length(min = 3, max = 15, message = "Please provide a valid title between 3-15 char")
  @Column(name = "title", nullable = false)
  @EntityUniqueFieldConfig(tableName = "coupons",columnName = "title" , message = "Company already have coupon with the same title")
  private String title;
  
  @Length(min = 15, max = 100, message = "Please provide a valid user name between 15-100 char")
  @Column(name = "description", nullable = false)
  private String description;
  
  @FutureOrPresent(message = "date must be after today")
  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;
  //todo !  valid date in service
  @Column(name = "end_date", nullable = false)
  private LocalDate endDate;

  @Min(value = 10,message = "Minimum amount is 10")
  @Max(value = 500,message = "Maximum amount is 1000")
  @Column(name = "amount", nullable = false)
  private int amount;
  
  @Min(value = 10,message = "Minimum amount is 10")
  @Max(value = 500,message = "Maximum amount is 1000")
  @Column(name = "price", nullable = false)
  private double price;
  
  @Pattern(regexp = "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$" ,message = "Must be a valid url")
  @Column(name = "image", nullable = false)
  private String image;
  
  @CreatedDate
  @Column(updatable = false)
  private LocalDate createdDate;
  @LastModifiedDate
  private LocalDate lastModifiedDate;
  
  @Column(name = "company_id")
  private int companyId;
  
  @PrePersist
  @PreUpdate
  public void convertFieldsToLowercase() {
    this.image = this.image.toLowerCase();
    this.description = this.description.toLowerCase();
  }
}
