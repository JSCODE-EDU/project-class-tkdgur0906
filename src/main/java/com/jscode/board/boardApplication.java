package com.jscode.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class boardApplication {

	public static void main(String[] args) {
		SpringApplication.run(boardApplication.class, args);
	}

}
