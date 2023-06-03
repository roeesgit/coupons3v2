package com.JbSchool.coupons3.security.entites.users;

import com.JbSchool.coupons3.app.utils.*;
import com.JbSchool.coupons3.security.auth.*;
import com.JbSchool.coupons3.security.exception.*;
import com.JbSchool.coupons3.security.token.*;
import lombok.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
@RequiredArgsConstructor
@Service
public class CouponUserService implements UserDetailsService {
  
  private final TokenConfig     tokenConfig;
  private final PasswordEncoder passwordEncoder;
  private final CouponUserRepo  couponUserRepo;
  
  /**
   * Loads a coupon user by their username.
   *
   * @param username The username of the coupon user.
   * @return The found coupon user.
   * @throws UsernameNotFoundException If the coupon user is not found.
   */
  @Override
  public CouponUser loadUserByUsername(String username) throws UsernameNotFoundException {
    return this.couponUserRepo.findByUsername(username);
  }
  
  /**
   * Adds a new coupon user.
   *
   * @param user The coupon user to add.
   * @return The generated token for the added user.
   * @throws Exception If an error occurs during user addition.
   */
  public TokenDTO addUser(CouponUser user) throws Exception {
    if (this.couponUserRepo.existsById(user.getId())) {
      throw new CouponException(AuthExceptionProvider.ID_ALREADY_EXIST.getMessage());
    }
    if (this.couponUserRepo.existsByUsername(user.getUsername())) {
      throw new CouponException(AuthExceptionProvider.EMAIL_ALREADY_EXIST.getMessage());
    }
    
    user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    this.couponUserRepo.save(user);
    String token = this.tokenConfig.generateToken(this.tokenConfig.buildClaims(user/*, (List <GrantedAuthority>) user.getAuthorities()*/));
    return new TokenDTO(token);
  }
  
  
  
  
}
