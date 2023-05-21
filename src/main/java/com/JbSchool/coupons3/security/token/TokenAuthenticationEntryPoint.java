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
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException authException)
  {
    resolver.resolveException(request,response , null , authException);
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
  }
  
  
}