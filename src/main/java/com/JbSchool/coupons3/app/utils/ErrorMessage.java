package com.JbSchool.coupons3.app.utils;

import lombok.*;

import java.util.*;
@AllArgsConstructor
@Getter
@Setter
@Builder
//@NoArgsConstructor
public class ErrorMessage {
  
//  private String header;
//  private String message;
  private Map <String, String> errors;
  
}
