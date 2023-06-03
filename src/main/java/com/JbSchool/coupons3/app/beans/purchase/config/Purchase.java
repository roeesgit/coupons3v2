package com.JbSchool.coupons3.app.beans.purchase.config;

import com.JbSchool.coupons3.app.beans.coupon.config.*;
import com.JbSchool.coupons3.app.beans.customer.config.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

/**
 * Represents a purchase in the system.
 */

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
  @Column(name = "customer_id")
  private int customerId;
  @Column(name = "coupon_id")
  private int couponId;
  
  
}
