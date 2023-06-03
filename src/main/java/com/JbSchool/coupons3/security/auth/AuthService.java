/**
 
 Service class for handling authentication-related operations.
 */
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
  /**
   
   Validates the login details provided by the user and generates a token if the details are valid.
   
   @param loginRequestDTO the login request data transfer object
   
   @return the token data transfer object
   
   @throws CouponException if an error occurs during the validation process
   */
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
  
  /**
   
   Checks if the login details provided by the user are valid.
   @param loginRequestDTO the login request data transfer object
   @return true if the login details are valid, false otherwise
   @throws CouponException if the login details are invalid
   */
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
