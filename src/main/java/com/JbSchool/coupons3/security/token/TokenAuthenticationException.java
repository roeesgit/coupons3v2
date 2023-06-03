/**
 
 TokenAuthenticationException is an exception class for token authentication errors.
 It extends AuthenticationException, which is a base class for authentication-related exceptions in Spring Security.
 */

package com.JbSchool.coupons3.security.token;

import lombok.*;

import javax.naming.*;
@Getter
public class TokenAuthenticationException extends AuthenticationException {
  /**
   
   Constructs a new TokenAuthenticationException with the specified detail message.
   @param message the detail message
   */
  public TokenAuthenticationException(String message) {
    super(message);
  }
}
