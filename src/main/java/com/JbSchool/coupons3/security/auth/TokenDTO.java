package com.JbSchool.coupons3.security.auth;

import lombok.*;

/**
 
 Data transfer object for a token.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
}
