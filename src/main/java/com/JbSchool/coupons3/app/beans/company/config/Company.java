package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.app.beans.coupon.config.*;

import com.JbSchool.coupons3.security.entites.users.*;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
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
@Table(name = "companies")
@EntityListeners(AuditingEntityListener.class)
public class Company {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @Column(name = "name", nullable = false)
  @Pattern(regexp = "[^as]",message = "No test allow???")
  @Length(min = 3, max = 15, message = "Please provide a valid user name between 3-15 char")
//  @EntityUniqueFieldConfig(tableName = "companies", columnName = "name",
//    message = "Name must be unique")
  private String name;
  
  // todo - check Flag.CASE_INSENSITIVE
  @Email(message = "Please provide a valid email address")
  @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address"
    , flags = Pattern.Flag.CASE_INSENSITIVE)
//  @EntityUniqueFieldConfig(tableName = "companies", columnName = "email",
//    message = "Email must be unique")
  @Column(name = "email", nullable = false)
  private String email;
  
  @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[/d])(?=.*[!@#$%^&*])(?=.{8,})",
    message = "Password must contain at least one uppercase letter, one lowercase letter," +
      "one digit, one special character, and be at least 8 characters long")
  @Column(name = "password")
  private String password;
  
 
  @CreatedDate
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
  @JsonIgnore
  @ToString.Exclude
  @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
  private List <Coupon> coupons;
  

  
}
