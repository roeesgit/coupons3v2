package com.JbSchool.coupons3.security.config;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configurers.*;
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
          regAuthConfig(reg);
          regCompaniesConfig(reg);
          regCustomerConfig(reg);
          regCouponsConfig(reg);
          reg.anyRequest().permitAll();
          
        })
      .addFilterBefore(couponSecurityFilter, UsernamePasswordAuthenticationFilter.class)
      .formLogin().disable()
      .csrf().disable()
      .build();
  }
  
  
  private void regAuthConfig(AuthorizeHttpRequestsConfigurer <HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers("/api/v1/auth/**").permitAll();
    System.out.println(1);
  }
  
  
  private void regCompaniesConfig(AuthorizeHttpRequestsConfigurer <HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers("/api/v1/companies/loggedCompany").hasAnyAuthority( "ROLE_COMPANY");
    reg.requestMatchers("/api/v1/companies/**").hasAnyAuthority("ROLE_ADMIN");
  }
  
  
  private void regCustomerConfig(AuthorizeHttpRequestsConfigurer <HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers(HttpMethod.GET, "/api/v1/customers/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
    reg.requestMatchers(HttpMethod.PUT, "/api/v1/customers/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
    reg.requestMatchers("/api/v1/customers/**").hasAnyAuthority("ROLE_ADMIN");
  }
  
  
  private void regCouponsConfig(AuthorizeHttpRequestsConfigurer <HttpSecurity>.AuthorizationManagerRequestMatcherRegistry reg) {
    reg.requestMatchers(HttpMethod.POST, "/api/v1/coupons/**").hasAnyAuthority("ROLE_COMPANY");
    reg.requestMatchers(HttpMethod.PUT, "/api/v1/coupons/**").hasAnyAuthority("ROLE_COMPANY");
    reg.requestMatchers(HttpMethod.DELETE, "/api/v1/coupons/**").hasAnyAuthority("ROLE_COMPANY");
    reg.requestMatchers(HttpMethod.GET, "/api/v1/coupons/getAll").hasAnyAuthority("ROLE_ADMIN");
    reg.requestMatchers(HttpMethod.GET, "/api/v1/coupons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_COMPANY", "ROLE_CUSTOMER");
    reg.requestMatchers("/api/v1/coupons/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_COMPANY");
  }
  
  
  
  
  
}
