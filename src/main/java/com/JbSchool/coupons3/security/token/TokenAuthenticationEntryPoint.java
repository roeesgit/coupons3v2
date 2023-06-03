/**
 An implementation of the {@link AuthenticationEntryPoint} interface that handles token authentication errors.
 */
package com.JbSchool.coupons3.security.token;

import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;

import java.io.*;
@Component("tokenAuthenticationEntryPoint")
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Autowired
  @Qualifier("handlerExceptionResolver")
  private  HandlerExceptionResolver resolver;
  
  /**
   
   Invoked when a token authentication error occurs.
   @param request the {@link HttpServletRequest} object
   @param response the {@link HttpServletResponse} object
   @param authException the {@link AuthenticationException} that occurred
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException)
  {
    resolver.resolveException(request,response , null , authException);
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }
  
  
}