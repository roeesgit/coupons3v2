package com.JbSchool.coupons3.entity_validator;

import jakarta.validation.*;

import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = EntityUniqueFieldValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityUniqueFieldConfig {
  String message() default "Value must be unique";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
  String tableName();
  String columnName();

}
