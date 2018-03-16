package com.easytestall.main;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.easytestall.controller","com.easytest.service.impl"})
@SpringBootApplication
public class EasyTestAllApplication {
	
    private static final Logger logger = Logger.getLogger(EasyTestAllApplication.class);
    
 	public static void main(String[] args) {
 		logger.info("工具开始启动。。。。。。。。。。。。");
		SpringApplication.run(EasyTestAllApplication.class, args);
	}
}
