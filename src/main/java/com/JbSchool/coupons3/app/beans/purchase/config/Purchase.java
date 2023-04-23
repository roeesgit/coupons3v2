package com.JbSchool.coupons3.app.beans.purchase.config;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "purchases")
public class Purchase {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  @OneToOne
  private Customer customer;
  
  @OneToOne
  private Coupon coupon;
  
  
}
