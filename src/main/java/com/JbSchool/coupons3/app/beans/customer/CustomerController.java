package com.JbSchool.coupons3.app.beans.customer;

import org.springframework.web.bind.annotation.*;
@RestController
  @RequestMapping("/api/v1/customers")
public class CustomerController {
  
  @PostMapping
  public String post() {
    return "CUSTOMER POST";
  }
  @PutMapping
  public String put() {
    return "CUSTOMER PUT";
  }
  @GetMapping
  public double get() {
    return 3.5;
  }
  @DeleteMapping
  public String delete() {
    return "CUSTOMER DELETE";
  }
  
}
