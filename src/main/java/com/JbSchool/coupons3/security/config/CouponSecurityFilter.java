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
@Component @AllArgsConstructor
public class CouponSecurityFilter extends OncePerRequestFilter {
  
  private final TokenConfig       tokenConfig;
  private final CouponUserService couponUserService
    ;
  
  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer")) {
      String token = authHeader.substring(7);
      String userName = this.tokenConfig.getUserNameFromToken(token);
      if (userName != null) {
        boolean isExpirationValid = this.tokenConfig.isExpirationTokenValid(token);
        if (isExpirationValid) {
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
    }
    filterChain.doFilter(request, response);
  }
  
  
}
