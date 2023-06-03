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
  
  
//  public boolean isExpirationTokenValid(String token) {
//    return new Date().before(this.getExpirationFromToken(token));
//  }
  
//  public Date getExpirationFromToken(String token) {
//    try {
//      return Jwts.parser()
//        .setSigningKey(secretKey)
//        .parseClaimsJws(token)
//        .getBody().getExpiration();
//    } catch (Exception e) {
//      return new Date(System.currentTimeMillis()-10000);
//    }
//  }
  
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
  
  public Map<String, Object> buildClaims(CouponUser user/*,ListGrantedAuthority> auth*/) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("auth" , user.getAuthorities());
    claims.put("id", user.getId());
    claims.put("username", user.getUsername());
    claims.put("loggedUserName", user.getLoggedUserName());
    return claims;
  }
  
}
