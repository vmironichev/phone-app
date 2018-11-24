package com.vmi.phone.catalog;

import com.vmi.phone.swagger.CommonSwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.vmi.phone")
@Import(CommonSwaggerConfiguration.class)
@EnableAutoConfiguration
public class PhoneCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneCatalogApplication.class, args);
	}
}
