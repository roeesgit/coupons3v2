package com.JbSchool.coupons3.security.config;

import lombok.*;
import org.apache.tomcat.websocket.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.*;
import org.springframework.security.web.access.channel.*;
import org.springframework.security.web.authentication.*;
@Configuration
@AllArgsConstructor
public class CouponsSecurityConfig {
  
  private final CouponSecurityFilter              couponSecurityFilter;
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests(
        (reg) -> {
        // LOGIN / REGISTER
        reg.requestMatchers("/api/v1/auth/**").permitAll();
        
        reg.requestMatchers(HttpMethod.PUT,"/api/v1/companies/**").hasAnyAuthority("ROLE_COMPANY");
        reg.requestMatchers(HttpMethod.GET,"/api/v1/companies/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_COMPANY");
        reg.requestMatchers(  "/api/v1/companies/**").hasAnyAuthority("ROLE_ADMIN");
        
        reg.requestMatchers("/api/v1/customers/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        
        reg.requestMatchers("/api/v1/coupons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_COMPANY");
//        reg.requestMatchers(HttpMethod.GET, "/api/v1/coupons/*").hasAnyAuthority("ROLE_CUSTOMER");
        reg.anyRequest().permitAll();
        
      })
      .addFilterBefore(couponSecurityFilter, UsernamePasswordAuthenticationFilter.class)
      .formLogin().disable()
      .csrf().disable()
      .build();
  }
  
  
  
  
  
}
