package br.com.awm.pantherproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableMongoRepositories
public class PantherProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PantherProductsApplication.class, args);
	}

}
