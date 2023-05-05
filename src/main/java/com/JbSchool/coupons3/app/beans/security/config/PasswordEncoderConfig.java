package com.JbSchool.coupons3.app.beans.security.config;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
@Configuration
@AllArgsConstructor
public class PasswordEncoderConfig {
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  
}
