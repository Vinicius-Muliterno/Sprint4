package br.com.compass.PartidosPoliticos.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compass.PartidosPoliticos"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(appInfo());
	}
	
	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title("Partidos Politicos Api Rest Compass")
				.description("Avaliação Sprint 4")
				.version("0.0.1")
				.license("Apache Licence Version 3.0")
				.contact(new Contact("Vinicius","https://github.com/Vinicius-Muliterno",""))
				.build();
	}
	
}
