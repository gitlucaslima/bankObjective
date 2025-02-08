package com.objective.bankObjective;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(security = {}, info = @Info(title = "Bank Objective API", version = "1.0", description = "API para simulação de transações bancárias"))
public class BankObjectiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankObjectiveApplication.class, args);
	}

}
