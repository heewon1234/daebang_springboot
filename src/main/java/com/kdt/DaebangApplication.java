package com.kdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DaebangApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaebangApplication.class, args);
		//System.out.println(new BCryptPasswordEncoder().encode("01012341234"));
	}

}
