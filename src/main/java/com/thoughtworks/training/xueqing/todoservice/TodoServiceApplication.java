package com.thoughtworks.training.xueqing.todoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.retry.annotation.EnableRetry;


@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
//@EnableRetry
public class TodoServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}
}
