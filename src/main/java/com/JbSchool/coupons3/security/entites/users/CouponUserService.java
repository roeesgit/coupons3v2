package com.JbSchool.coupons3.security.entites.users;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;
import com.JbSchool.coupons3.security.exception.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
@RequiredArgsConstructor @Service
public class CouponUserService implements UserDetailsService {
  
  private final TokenConfig     tokenConfig;
  private final PasswordEncoder passwordEncoder;
  private final CouponUserRepo couponUserRepo;
  @Override
  public CouponUser loadUserByUsername(String username) throws UsernameNotFoundException {
   return this.couponUserRepo.findByUsername(username);
  }
  public TokenResponseDTO addUser(CouponUser user) throws Exception {
    if (this.couponUserRepo.existsById(user.getId())) {
      throw new CouponException(AuthExceptionProvider.ID_ALREADY_EXIST.getMessage());
    }
    if (this.couponUserRepo.existsByUsername(user.getUsername())) {
      throw new CouponException(AuthExceptionProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
    
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    this.couponUserRepo.save(user);
    String token = this.tokenConfig.generateToken(this.tokenConfig.buildClaims(user/*, (List <GrantedAuthority>) user.getAuthorities()*/));
    return new TokenResponseDTO(token);
  }
  
  

  
}
