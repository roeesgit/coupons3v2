/**
 * The {@code CompanyDto} class represents a data transfer object (DTO) for the company entity.
 * It contains simplified information about the company, such as its ID, name, and email.
 */
package com.JbSchool.coupons3.app.beans.company.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The {@code CompanyDto} class represents a data transfer object (DTO) for the company entity.
 * It contains simplified information about the company, such as its ID, name, and email.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyDto {
  
  private int id;
  private String name;
  private String email;
  
}