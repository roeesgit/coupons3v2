package com.JbSchool.coupons3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.transaction.annotation.*;

@SpringBootApplication @EnableJpaAuditing @EnableTransactionManagement
public class Coupons3Application {

	public static void main(String[] args) {
		SpringApplication.run(Coupons3Application.class, args);
	}

}
