package com.JbSchool.coupons3.security.entites.auth;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Builder
@Entity
@Scope("prototype")
@Table(name = "auth")
  public class CouponAuth {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private CouponAuthorization couponAuthorization;
    
  
  
}
