package com.JbSchool.coupons3.security.entites.users;
import com.JbSchool.coupons3.security.entites.auth.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Scope("prototype")
@Table(name = "coupon_users")
public class  CouponUser implements UserDetails {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String username;
    private String password;
    
  @ManyToMany(fetch = FetchType.EAGER ,cascade = {CascadeType.REMOVE})
  @JoinTable(name = "coupon_users_auth",
  joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "auth_id"))
  private List <CouponAuth> couponAuths;
//  private List <GrantedAuthority> couponAuths;
  
  
  @Override
  public Collection <? extends GrantedAuthority> getAuthorities() {
    System.out.println("*********************");
    System.out.println(this.couponAuths);
    List <GrantedAuthority> authorities = new ArrayList <>();
    this.couponAuths.forEach(cA-> authorities.add(cA.getCouponAuthorization()::name));
    return authorities;
//    return this.couponAuths;
  }
  
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  
  @Override
  public boolean isEnabled() {
    return true;
  }
  
  
}
