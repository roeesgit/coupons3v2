package com.JbSchool.coupons3;

import com.JbSchool.coupons3.app.beans.coupon.facade.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication @EnableJpaAuditing
/*Main*/
public class Coupons3Application {
	/*Coupon service attribute */
	@Autowired
	private CouponServiceImpl couponService;
	public static void main(String[] args) {
		SpringApplication.run(Coupons3Application.class, args);
	}
	
	
}
