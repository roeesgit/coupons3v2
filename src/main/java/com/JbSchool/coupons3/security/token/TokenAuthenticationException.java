package com.JbSchool.coupons3.security.token;

import lombok.*;

import javax.naming.*;
@Getter
public class TokenAuthenticationException extends AuthenticationException {
  public TokenAuthenticationException(String message) {
    super(message);
  }
}
