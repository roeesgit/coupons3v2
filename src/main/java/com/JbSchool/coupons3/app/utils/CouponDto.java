package com.JbSchool.coupons3.app.utils;

import com.JbSchool.coupons3.app.beans.category.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponDto {
  
  @Enumerated(EnumType.STRING)
  private CategoryProvider category;
  
  private String    description;
  private LocalDate endDate;
  private int       amount;
  private double    price;
  private String    image;
  
  
}
