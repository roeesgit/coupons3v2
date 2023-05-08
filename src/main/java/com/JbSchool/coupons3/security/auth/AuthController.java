package com.JbSchool.coupons3.security.auth;

import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  
  private final AuthService       authService;
  private final CouponUserService couponUserService;
  
  
  @PostMapping("/login")
  public TokenDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
    TokenDTO t = this.authService.validateLoginDetails(loginRequestDTO);
    System.out.println(t);
    return t;
  }
  
  
}