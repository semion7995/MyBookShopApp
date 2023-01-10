package com.example.MyBookShopApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class MyBookShopAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyBookShopAppApplication.class, args);
	}
}
