package com.JbSchool.coupons3.security.config;

import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
@Configuration
@AllArgsConstructor
public class AuthenticationManagerConfig {
  
  private final CouponUserService couponUserService;
  
  
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(couponUserService)
      .and().build();
  }
  
  
}
