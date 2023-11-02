package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.config.CommonConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(title = CommonConstants.SOFTWARE_NAME + " API RestTemplate or WebClient", 
								version = "1.0.3", 
								description = "API documentation for Sprint 5.1.3 Flower Connection",
								termsOfService = "Free to use",
								contact = @Contact(name = "Christian", email = "email.com"),
								license = @License(name = "API License", 
											url = "Affiliated " + CommonConstants.SOFTWARE_NAME + " website to be placed here")))
@EnableAutoConfiguration
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class S05T01N03TemplateWeb {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N03TemplateWeb.class, args);
	}
	
	@Bean
	WebClient getWebClient() {
		return WebClient.builder().build();
	}
	
}