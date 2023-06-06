/**
 
 CouponSecurityFilter is a filter that intercepts incoming requests and validates the JWT token in the Authorization header.
 It retrieves the user details from the token and sets the authentication in the SecurityContextHolder.
 */
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
  
  /**
   
   Performs the filtering logic for each incoming request.
   @param request the HttpServletRequest object
   @param response the HttpServletResponse object
   @param filterChain the FilterChain object
   @throws ServletException if any servlet exception occurs
   @throws IOException if an I/O exception occurs
   */  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer")) {
      String token = authHeader.substring(7);
      String userName = this.tokenConfig.getUserNameFromToken(token);
      if (userName != null) {
          CouponUser couponUser = this.couponUserService.loadUserByUsername(userName);
          if (couponUser != null) {
            UsernamePasswordAuthenticationToken auth =
              new UsernamePasswordAuthenticationToken(
                couponUser, null,couponUser.getAuthorities()
              );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
      }
    }
    filterChain.doFilter(request, response);
  }
  
  
}
