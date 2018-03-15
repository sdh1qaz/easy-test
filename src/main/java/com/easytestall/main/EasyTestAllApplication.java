package com.easytestall.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.easytestall.controller","com.easytest.service.impl"})
@SpringBootApplication
public class EasyTestAllApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(EasyTestAllApplication.class, args);
	}
}
