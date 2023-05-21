package com.JbSchool.coupons3.security.config;

import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;
@Configuration
@EnableWebSecurity
public class CouponsSecurityConfig {
  @Autowired
  private  CouponSecurityFilter couponSecurityFilter;
  
  @Autowired
  @Qualifier("tokenAuthenticationEntryPoint")
  AuthenticationEntryPoint authEntryPoint;
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeHttpRequests(
        (reg) -> {
          
          // AUTH
          reg.requestMatchers("/api/v1/auth/**").permitAll();
          reg.requestMatchers(HttpMethod.GET , "/api/v1/coupons/all").permitAll();
  
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
          reg.anyRequest().authenticated();
          
        })
      .addFilterBefore(couponSecurityFilter, UsernamePasswordAuthenticationFilter.class)
      .exceptionHandling().authenticationEntryPoint(authEntryPoint)
      .and()
      .formLogin().disable()
      .csrf().disable()
      .cors().and()
      .build();
  }
//  @Bean
//  public InMemoryUserDetailsManager userDetailsService() {
//    UserDetails admin = User.withUsername("admin")
//      .password("admin")
//      .roles("ROLE_ADMIN")
//      .build();
//    return new InMemoryUserDetailsManager(admin);
//  }
}
