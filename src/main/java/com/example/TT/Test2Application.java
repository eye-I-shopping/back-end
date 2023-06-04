package com.example.TT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan(basePackages = {"com.example.TT.item"})
public class Test2Application {

	public static void main(String[] args) {
		SpringApplication.run(Test2Application.class, args);
	}

}
