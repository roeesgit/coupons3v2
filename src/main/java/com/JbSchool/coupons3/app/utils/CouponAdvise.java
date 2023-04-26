package com.JbSchool.coupons3.app.utils;

import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestControllerAdvice
public class CouponAdvise {
  
  @ExceptionHandler(CouponException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage companyAdviseHandler(Exception e) {
    ErrorMessage errorMessage = ErrorMessage.builder().build();
    Map <String, String> errors = new HashMap <>();
    errors.put("Error", e.getMessage());
    errorMessage.setErrors(errors);
    return errorMessage;
  }
  //todo return this aster back-end testing
//
//  @ExceptionHandler(Exception.class)
//  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//  public ErrorMessage fatalErrorHandler(Exception e) {
//    ErrorMessage errorMessage = ErrorMessage.builder().build();
//    Map <String, String> errors = new HashMap <>();
//    errors.put("fatal error", e.getMessage());
//    errorMessage.setErrors(errors);
//    return errorMessage;
//  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public ErrorMessage entityValidationHandler(MethodArgumentNotValidException e ){
    ErrorMessage errorMessage = ErrorMessage.builder().build();
    Map <String, String> errors = new HashMap <>();
    if (e.getBindingResult().getFieldErrors().size() < 1) {
      errors.put("Entity error", "email or name already exist");
    } else {

      e.getBindingResult().getFieldErrors().forEach(error->
        errors.put(error.getField(), error.getDefaultMessage())
      );
    }
    errorMessage.setErrors(errors);
    return errorMessage;  }



}