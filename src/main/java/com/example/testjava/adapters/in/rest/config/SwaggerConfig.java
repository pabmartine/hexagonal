package com.example.testjava.adapters.in.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class for Swagger
 * @author pmarti14
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * Generate the swagger documentation
	 * @return Swagger configuration object
	 */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.testjava.adapters.in.rest.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}