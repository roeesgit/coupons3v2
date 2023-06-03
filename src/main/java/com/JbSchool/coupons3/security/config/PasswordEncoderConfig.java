/**
 
 Configuration class for password encoder.
 */
package com.JbSchool.coupons3.security.config;

import lombok.*;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
@Configuration
@AllArgsConstructor
public class PasswordEncoderConfig {
  /**
   
   Creates and returns an instance of BCryptPasswordEncoder as the password encoder.
   @return the BCryptPasswordEncoder instance
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  
}
