package com.JbSchool.coupons3.app.beans.customer.config;


import lombok.*;
/**
 
 Data Transfer Object (DTO) for representing a customer.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerDto {
  private int id;
  private String firstName;
  private String lastName;
  private String email;
  
}
