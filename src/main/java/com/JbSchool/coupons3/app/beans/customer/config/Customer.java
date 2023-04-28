package com.JbSchool.coupons3.app.beans.customer.config;

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
  @EntityUniqueFieldConfig(tableName = "customers",columnName = "id" , message = "Id already exist")
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
  
  @Pattern(
    regexp = "^(?=.*\\d)(?=.*[a-z])(?=[A-Z])(?=.*[@#$%^&+=])(?!.*\\s).{8,}.*$"
    ,message =
    "Password must contain at least one digit" +
      ", at least one lowercase letter" +
      ", at least one uppercase letter" +
      ", at least one special character" +
      ", must not contain spaces" +
      ", and must be at least 8 characters long" +
      "")
  @Column(name = "password", nullable = false)
  private String password;
  

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
//  @ToString.Exclude
//  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
//  private List <Purchase> customerCoupons;
  
  
}
