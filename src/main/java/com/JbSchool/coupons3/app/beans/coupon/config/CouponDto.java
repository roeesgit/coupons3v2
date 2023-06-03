package com.JbSchool.coupons3.app.beans.coupon.config;

import lombok.*;

import java.time.*;
/**
 
 The CouponDto class represents a data transfer object for the Coupon entity.
 */
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
