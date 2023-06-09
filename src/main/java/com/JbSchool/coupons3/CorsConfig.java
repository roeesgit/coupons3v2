/**
 
 CorsConfig configures Cross-Origin Resource Sharing (CORS) for the application.
 */
package com.JbSchool.coupons3;

import org.springframework.context.annotation.*;
import org.springframework.web.cors.*;
import org.springframework.web.filter.*;

import java.util.*;
@Configuration
/*This class define cors filter.*/
public class CorsConfig {
  /**
   
   Configures the CORS filter to allow requests from specified origins, headers, and methods.
   @return the configured CorsFilter
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    config.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
  
  
}
