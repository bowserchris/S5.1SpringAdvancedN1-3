package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
//@EnableOpenApi
//@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {
	
	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title(CommonConstants.SOFTWARE_NAME + " API")
						.description(CommonConstants.SOFTWARE_NAME + " demo application")
						.version("v1.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description(CommonConstants.SOFTWARE_NAME + " Wiki Documentation")
						.url("website"));
	}
	
	/*@Bean
	Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)	//or .SWAGGER2 .OAS_30
				.select()
				.apis(RequestHandlerSelectors.any()) // or instead of .any use basePackage("cat.itacademy.barcelonactiva.flor.s05.t01.n02.S05T01N02Flor.controller"))
				.paths(PathSelectors.any())
				.build();
				/*.useDefaultResponseMessages(false)
				.globalResponses(HttpMethod.GET, new ArrayList(
				    new ResponseBuilder().code("500")
				        .description("500 message").build(),
				    new ResponseBuilder().code("403")
				        .description("Forbidden!!!!!").build()
				));*//*
	}*/
	
	/*private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "My REST API", 
	      "Some custom description of API.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("John Doe", "www.example.com", "[email protected]"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
	*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) 
	{
		//enabling swagger-ui part for visual documentation
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	*
	*/
	
	/*
	 * or
	 * 
	 * @Bean
	 * Docket api() {
	 * return new Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestedHandlerSelectors.withClassAnnotation(RestController.class))
	 * .paths(PathSelectors.any())
	 * .build();
	 * }
	 * 
	 */
}
