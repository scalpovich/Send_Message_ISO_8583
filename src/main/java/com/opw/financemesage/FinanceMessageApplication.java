package com.opw.financemesage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class FinanceMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceMessageApplication.class, args);
	}

}
