package com.JbSchool.coupons3.app.utils;

import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.*;
@RestControllerAdvice
public class CouponAdvise {
  
  @ExceptionHandler({CouponException.class, AuthenticationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage companyAdviseHandler(Exception e) {
    return ErrorMessage.builder()
      .error("Error")
      .message(e.getMessage())
      .build();
  }
  
  
  //todo return this aster back-end testing
//
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//  public ErrorMessage fatalErrorHandler(Exception e) {
//    ErrorMessage errorMessage = ErrorMessage.builder()
//      .error("fatal error")
//      .message(e.getMessage())
//      .build();
//    System.out.println(errorMessage.getMessage());
//    return errorMessage;
//  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorMessage entityValidationHandler(MethodArgumentNotValidException e) {
    AtomicReference <String> buildError = new AtomicReference <>("");
    e.getBindingResult().getFieldErrors().forEach(er ->
      buildError.set(buildError + er.getField() + ": " + er.getDefaultMessage() + "\n")
    );
    String errorMessageValue = String.valueOf(buildError);
    ErrorMessage errorMessage = ErrorMessage.builder()
      .error("Entity Error")
      .message(errorMessageValue)
      .build();
    
    System.out.println(errorMessage.getMessage());
    return errorMessage;
  }
  
  
  
}