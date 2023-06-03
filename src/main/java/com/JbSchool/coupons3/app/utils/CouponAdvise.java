package com.JbSchool.coupons3.app.utils;

import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.*;

/**
 * Advice class for handling exceptions and providing error messages.
 */
@RestControllerAdvice
public class CouponAdvise {
  /**
   * Handles CouponException and AuthenticationException and returns an error message.
   *
   * @param e the exception to be handled
   * @return the error message
   */
  @ExceptionHandler({CouponException.class, AuthenticationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorMessage couponAdviseHandler(Exception e) {
    return ErrorMessage.builder()
      .error("Error")
      .message(e.getMessage())
      .build();
  }
  
  
  /**
   * Handles any other exception and returns an error message with the status "I_AM_A_TEAPOT".
   *
   * @param e the exception to be handled
   * @return the error message
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public ErrorMessage fatalErrorHandler(Exception e) {
    ErrorMessage errorMessage = ErrorMessage.builder()
      .error("fatal error")
      .message(e.getMessage())
      .build();
    System.out.println(errorMessage.getMessage());
    return errorMessage;
  }
  
  
  /**
   * Handles MethodArgumentNotValidException and returns an error message for entity validation.
   *
   * @param e the exception to be handled
   * @return the error message
   */
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