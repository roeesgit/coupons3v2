/**
 
 Controller class for handling authentication-related endpoints.
 */
package com.JbSchool.coupons3.security.auth;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  
  private final AuthService       authService;
  private final CouponUserService couponUserService;
  
  /**
   
   Handles the login endpoint for user authentication.
   @param loginRequestDTO the login request data transfer object
   @return the token data transfer object
   @throws CouponException if an error occurs during the login process
   */
  @PostMapping("/login")
  public TokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws CouponException {
    TokenDTO t = this.authService.validateLoginDetails(loginRequestDTO);
    System.out.println(t);
    return t;
  }
  
  /**
   
   Handles the isConnected endpoint to check if there is basic connection.
   */
  @GetMapping("/isConnected")
  public void isConnected() {
  }
  
  
}