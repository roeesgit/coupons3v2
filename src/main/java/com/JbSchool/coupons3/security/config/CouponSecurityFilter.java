package com.JbSchool.coupons3.security.config;

import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import java.io.*;
@Component @AllArgsConstructor
public class CouponSecurityFilter extends OncePerRequestFilter {
  
  private final TokenConfig       tokenConfig;
  private final CouponUserService couponUserService
    ;
  
  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer")) {
      String token = authHeader.substring(7);
      boolean isExpirationValid1 = this.tokenConfig.isExpirationTokenValid(token);
      System.out.println(isExpirationValid1);
      String userName = this.tokenConfig.getUserNameFromToken(token);
      if (userName != null) {
        boolean isExpirationValid = this.tokenConfig.isExpirationTokenValid(token);
        System.out.print(" ,exp token: " +isExpirationValid);
        if (isExpirationValid) {
          CouponUser couponUser = (CouponUser) this.couponUserService.loadUserByUsername(userName);
          System.out.println(couponUser);
          if (couponUser != null) {
            UsernamePasswordAuthenticationToken auth =
              new UsernamePasswordAuthenticationToken(
                couponUser, null,couponUser.getAuthorities()
              );
            System.out.println("auth "+auth);
            SecurityContextHolder.getContext().setAuthentication(auth);
          }
        }
      }
    }
    filterChain.doFilter(request, response);
    
    
    
  }
  
  
}