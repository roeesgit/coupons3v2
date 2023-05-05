package com.JbSchool.coupons3.app.beans.security.entites.users;

import com.JbSchool.coupons3.app.beans.security.entites.coupon_users_auth.*;
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
@Table(name = "users")
public class CouponUser implements UserDetails {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  
  private String username;
  private String password;
  
  @ToString.Exclude
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "couponUser")
  private List <CouponUserAuth> couponUserAuths;
  
  
  @Override
  public Collection <? extends GrantedAuthority> getAuthorities() {
    List <GrantedAuthority> authorities = new ArrayList <>();
    this.couponUserAuths.forEach(cA -> authorities.add(cA.getCouponAuth().getCouponAuthorization()::name));
    return authorities;
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
