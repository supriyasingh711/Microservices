package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST APIs Documentation",
				description = "Banks Accounts Microservice REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Supriya Singh",
						email = "singhsupriya711@gmail.com",
						url = "https://mellifluous-lollipop-d545ed.netlify.app/"
				),
				license = @License(
						name = "Apache 2.0",
						url="https://mellifluous-lollipop-d545ed.netlify.app/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts Microservice Documentation",
				url = "https://mellifluous-lollipop-d545ed.netlify.app/"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
