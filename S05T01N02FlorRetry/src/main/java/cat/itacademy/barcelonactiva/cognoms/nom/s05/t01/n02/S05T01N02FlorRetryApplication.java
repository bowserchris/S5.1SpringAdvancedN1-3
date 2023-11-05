package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.config.CommonConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = CommonConstants.SOFTWARE_NAME + " API", 
version = "1.0.1", 
description = "API documentation for Sprint 5.1 Flower Project",
termsOfService = "Free to use",
contact = @Contact(name = "Christian", email = "email.com"),
license = @License(name = "API License", 
			url = "Affiliated " + CommonConstants.SOFTWARE_NAME + " website to be placed here")))
@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class S05T01N02FlorRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02FlorRetryApplication.class, args);
	}

}
