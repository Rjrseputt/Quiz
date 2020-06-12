package com.symphox.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
//	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
//			Arrays.asList("application/json"));

	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(buildApiInfo())
//				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
//				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.symphox.quiz.controller"))
				.paths(PathSelectors.regex("/.*"))
				.build();
	}

	private ApiInfo buildApiInfo() {

		return new ApiInfoBuilder()
				.title("Symphox Quiz API Document")
				.description("Provide all API interfaces of the Symphox Quiz")
				.version("1.0")
				.build();
	}
}