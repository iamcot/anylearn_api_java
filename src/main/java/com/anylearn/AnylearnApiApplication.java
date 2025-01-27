package com.anylearn;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AnylearnApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnylearnApiApplication.class, args);
	}
}