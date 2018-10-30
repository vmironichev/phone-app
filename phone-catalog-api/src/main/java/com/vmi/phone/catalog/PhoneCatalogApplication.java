package com.vmi.phone.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PhoneCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneCatalogApplication.class, args);
	}
}
