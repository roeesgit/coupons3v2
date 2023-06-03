package com.JbSchool.coupons3.security.auth;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.entites.users.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AuthService {
  
  private final AuthenticationManager authenticationManager;
  private final TokenConfig           tokenConfig;
  private final CouponUserService     couponUserService;
  
  
  public TokenDTO validateLoginDetails(LoginRequestDTO loginRequestDTO) throws CouponException {
    boolean isLoginDetailsValid = this.isLoginDetailsValid(loginRequestDTO);
    if (isLoginDetailsValid) {
      CouponUser user = this.couponUserService.loadUserByUsername(loginRequestDTO.getUsername());
      
      String token = this.tokenConfig.generateToken(this.tokenConfig
        .buildClaims(user));
      return new TokenDTO(token);
    }
    return null;
  }
  
  
  private boolean isLoginDetailsValid(LoginRequestDTO loginRequestDTO) throws CouponException {
    try {
      this.authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          loginRequestDTO.getUsername(),
          loginRequestDTO.getPassword()
        )
      );
    } catch (Exception e) {
      throw new CouponException(ErrorMessageProvider.BAD_CREDENTIALS.getMessage());
    }
    return true;
  }
  
  
}
