/**
 
 TokenConfig is responsible for generating and validating JWT tokens.
 */
package com.JbSchool.coupons3.security.token;

import com.JbSchool.coupons3.security.entites.users.*;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;
@Service

public class TokenConfig {
  private final String secretKey;
  
  public TokenConfig(@Value("${couponKey}") String appSecretToken) {
    this.secretKey = appSecretToken;
  }
  
  /**
   
   Generates a JWT token based on the provided claims.
   @param claims the claims to include in the token
   @return the generated JWT token
   */
  public String generateToken(Map <String, Object> claims) {
     // TODO: 11/22/2022 test
    System.out.println(System.currentTimeMillis()  + (1000*20) );
    return Jwts.builder()
      .addClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis()  + (1000*60*60*60) ))
//      .setExpiration(new Date(System.currentTimeMillis()  + (1000*6) ))
      .signWith(SignatureAlgorithm.HS256, secretKey)
      .compact();
  }
  
  
  /**
   
   Retrieves the username from the provided JWT token.
   @param token the JWT token
   @return the username retrieved from the token
   @throws IOException if an error occurs while parsing the token
   */
  public String getUserNameFromToken(String token) throws IOException {
    try {
      return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .get("username").toString();
    } catch (ExpiredJwtException e) {
      System.out.println(e.getClass());
      throw new IOException(e.getMessage());
    }
  }
  /**
   
   Builds the claims to be included in the JWT token based on the provided user.
   @param user the user for whom the token is generated
   @return the claims for the token
   */
  public Map<String, Object> buildClaims(CouponUser user/*,ListGrantedAuthority> auth*/) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("auth" , user.getAuthorities());
    claims.put("id", user.getId());
    claims.put("username", user.getUsername());
    claims.put("loggedUserName", user.getLoggedUserName());
    return claims;
  }
  
}
