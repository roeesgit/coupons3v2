package com.JbSchool.coupons3.security.auth;

import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthService {
  
  private final AuthenticationManager authenticationManager;
  private final TokenConfig       tokenConfig;
  private final CouponUserService couponUserService;
  
  
  public TokenResponseDTO validateLoginDetails(LoginRequestDTO loginRequestDTO) {
    boolean isLoginDetailsValid = this.isLoginDetailsValid(loginRequestDTO);
    if (isLoginDetailsValid) {
      CouponUser user = this.couponUserService.loadUserByUsername(loginRequestDTO.getUsername());
  
      String token = this.tokenConfig.generateToken(this.tokenConfig
        .buildClaims(user/*,(Set <GrantedAuthority>) user.getAuthorities()*/));
      return new TokenResponseDTO(token);
    }
    return null;
  }
  
  
  private boolean isLoginDetailsValid(LoginRequestDTO loginRequestDTO) {
    System.out.println(loginRequestDTO.getUsername());
    try {
      this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          loginRequestDTO.getUsername(),
          loginRequestDTO.getPassword()
        )
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  
  
}
