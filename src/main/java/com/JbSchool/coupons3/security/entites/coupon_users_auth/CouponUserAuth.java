package com.JbSchool.coupons3.security.entites.coupon_users_auth;
import com.JbSchool.coupons3.security.entites.auth.*;
import com.JbSchool.coupons3.security.entites.users.*;
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
@Table(name = "users_auth")
public class CouponUserAuth {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
  
  @OneToOne
  private CouponUser couponUser;
  @OneToOne
  private CouponAuth couponAuth;
}
