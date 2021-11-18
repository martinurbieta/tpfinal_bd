package com.bd.tpfinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TpfinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpfinalApplication.class, args);
	}

}
