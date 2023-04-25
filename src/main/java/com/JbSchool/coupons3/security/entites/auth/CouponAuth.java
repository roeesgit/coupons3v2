package com.JbSchool.coupons3.security.entites.auth;
import com.JbSchool.coupons3.security.entites.users.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Builder
@Entity
@Scope("prototype")
@Table(name = "coupon_auth")
  public class CouponAuth {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private CouponAuthorization couponAuthorization;
    
@ToString.Exclude
  @ManyToMany(mappedBy = "couponAuths")
  private List <CouponUser> couponUserSet;
  
  
//  @Override
//  public String getAuthority() {
//    return couponAuthorization.name();
//  }
  
  
}
