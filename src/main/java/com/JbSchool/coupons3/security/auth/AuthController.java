package com.JbSchool.coupons3.security.auth;

import com.JbSchool.coupons3.security.entites.users.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService       authService;
    private final CouponUserService couponUserService;

    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return this.authService.validateLoginDetails(loginRequestDTO);
    }

    @PostMapping("/registration")
    public TokenResponseDTO registration(@RequestBody CouponUser user) throws Exception {
        return this.couponUserService.addUser(user);
    }

//    @PostMapping
//    public Company addCompany(@RequestBody Company company) {
//        return //...
//    }
}
