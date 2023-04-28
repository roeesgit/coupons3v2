package com.JbSchool.coupons3.app.beans.company.config;

import com.JbSchool.coupons3.entity_validator.*;
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
  @EntityUniqueFieldConfig(tableName = "companies",columnName = "id" , message = "Id already exist")
  private int id;
  
  @Column(updatable = false, name = "name", nullable = false)
//  @Pattern(regexp = "^(?![fuck]).*$",message = "No fu#@ allowed!")
  @Length(min = 3, max = 15, message = "Please provide a valid user name between 3-15 char")
  @EntityUniqueFieldConfig(tableName = "companies", columnName = "name",
    message = "Name must be unique")
  private  String name;
  
  @Email(message = "Please provide a valid email address")
  @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
  @EntityUniqueFieldConfig(tableName = "companies", columnName = "email",
    message = "Email must be unique")
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
  @Column(name = "password")
  private String password;
  
 
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdDate;
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
  
//  @JsonIgnore
//  @ToString.Exclude
//  @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE})
//  private List <Coupon> coupons;
  

  
}
