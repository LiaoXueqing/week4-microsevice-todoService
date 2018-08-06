package com.thoughtworks.training.xueqing.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class TodoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}
}
