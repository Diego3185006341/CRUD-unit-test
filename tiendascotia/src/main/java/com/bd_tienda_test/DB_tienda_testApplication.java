package com.bd_tienda_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class DB_tienda_testApplication {

	public static void main(String[] args) {
		SpringApplication.run(DB_tienda_testApplication.class, args);
	}

}
