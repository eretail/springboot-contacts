package com.yingchun.singlestone.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("com.yingchun.singlestone.assignment.model")
@EnableJpaRepositories("com.yingchun.singlestone.assignment.repo")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.yingchun.singlestone.assignment"})

public class SingleStoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingleStoneApplication.class, args);
	}
}
