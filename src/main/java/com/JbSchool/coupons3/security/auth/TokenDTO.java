/**
 
 Data transfer object for a token.
 */
package com.JbSchool.coupons3.security.auth;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private String token;
}
