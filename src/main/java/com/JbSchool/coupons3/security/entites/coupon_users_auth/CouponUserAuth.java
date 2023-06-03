package com.JbSchool.coupons3.security.entites.coupon_users_auth;

import com.JbSchool.coupons3.security.entites.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

/**
 * Represents the authentication information for a coupon user.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "users_auth")
public class CouponUserAuth {
  /**
   * The ID of the coupon user authentication.
   */
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  /**
   * The associated coupon user.
   */
  @OneToOne
  private CouponUser couponUser;
  /**
   * The associated coupon authentication.
   */
  @OneToOne
  private CouponAuth couponAuth;
}
