package com.JbSchool.coupons3.security.config;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
@Configuration
@AllArgsConstructor
public class CouponsSecurityConfig {
  
  private final CouponSecurityFilter couponSecurityFilter;
  
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests(
        (reg) -> {
          
          // AUTH
          reg.requestMatchers("/api/v1/auth/**").permitAll();
  
          // COMPANIES
          reg.requestMatchers("/api/v1/companies/loggedCompany").hasAuthority( "ROLE_COMPANY");
          reg.requestMatchers("/api/v1/companies/**").hasAuthority("ROLE_ADMIN");
          
          // CUSTOMERS
          reg.requestMatchers( "/api/v1/customers/loggedCustomer").hasAuthority("ROLE_CUSTOMER");
          reg.requestMatchers("/api/v1/customers/**").hasAuthority("ROLE_ADMIN");
          
          // COUPONS
          reg.requestMatchers( "/api/v1/coupons/customer/**").hasAuthority("ROLE_CUSTOMER");
          reg.requestMatchers( "/api/v1/coupons/**").hasAuthority("ROLE_COMPANY");
          
          //ANY UNSPECIFIED
          reg.anyRequest().permitAll();
          
        })
      .addFilterBefore(couponSecurityFilter, UsernamePasswordAuthenticationFilter.class)
      .formLogin().disable()
      .cors().and()
      .csrf().disable()
      .build();
  }
  
}
