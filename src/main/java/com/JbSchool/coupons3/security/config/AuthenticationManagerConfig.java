/**
 
 AuthenticationManagerConfig configures the authentication manager for the application.
 */
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
  
  /**
   
   Configures the authentication manager with the provided user details service.
   @param httpSecurity the HttpSecurity object
   @return the configured AuthenticationManager
   @throws Exception if an exception occurs during configuration
   */
  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(couponUserService)
      .and().build();
  }
  
  
}
