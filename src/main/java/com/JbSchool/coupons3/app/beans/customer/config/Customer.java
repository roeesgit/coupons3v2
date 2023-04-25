package com.JbSchool.coupons3.app.beans.customer.config;

import com.JbSchool.coupons3.app.beans.purchase.config.*;

import com.JbSchool.coupons3.security.entity_validator.*;
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
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
public class Customer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Length(min = 3, max = 15, message = "Please provide a valid first name between 2-15 char")
  @Column(name = "first_name", nullable = false)
  private String firstName;
  
  @Length(min = 3, max = 15, message = "Please provide a valid last name between 2-15 char")
  @Column(name = "last_name", nullable = false)
  private String lastName;
  
  @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
  @EntityUniqueFieldConfig(tableName = "customers", columnName = "email", message = "Email must be unique")
  @Column(name = "email", nullable = false)
  private String email;
  
  @Column(name = "password", nullable = false)
  private String password;
  

  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
  @ToString.Exclude
  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
  private List <Purchase> customerCoupons;
  
  
}
