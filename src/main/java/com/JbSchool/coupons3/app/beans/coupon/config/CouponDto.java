package com.JbSchool.coupons3.app.beans.coupon.config;

import com.JbSchool.coupons3.app.beans.category.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CouponDto {
  
  
  private int id;
  private String category;
  private String    title;
  private String    description;
  private LocalDate startDate;
  private LocalDate endDate;
  private int       amount;
  private double    price;
  private String    image;
  private int companyId;
  
}
