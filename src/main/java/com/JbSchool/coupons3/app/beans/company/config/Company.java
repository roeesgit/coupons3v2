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
import org.springframework.security.core.context.*;

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
  private int id;
  
  @Column(updatable = false, name = "name", nullable = false)
  @Length(min = 3, max = 15, message = "Please provide a valid user name between 3-15 char")
  private  String name;
  
  @Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
  @Length(min = 3, max = 25, message = "Please provide a valid user name between 3-25 char")
  @Column(name = "email", nullable = false)
  private String email;
  
  @Length(min = 3, max = 70, message = "Please provide a valid password between 3-70 char")
  @Pattern(
    regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!.*\\s).{8,}$"
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
  
  
  @PrePersist
  @PreUpdate
  public void convertFieldsToLowercase() {
    this.name = this.name.toLowerCase();
    this.email = this.email.toLowerCase();
  }
}
