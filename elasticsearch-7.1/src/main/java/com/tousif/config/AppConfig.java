package com.tousif.config;


import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Instead of servlateName-servlet.xml
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = "com.tousif")
//@Import({SearchClient.class, /*ServiceContext.class*/})
public class AppConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.paths(PathSelectors.ant("vca-web/api/*"))
//				.apis(RequestHandlerSelectors.basePackage("com.cordis"))
				.apis(RequestHandlerSelectors.basePackage("com.tousif"))
				.build()
				.apiInfo(apiDetails());
		
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo("Elasticsearch 7.2 Api Documentation",
				null,null, null, null, null, null, Collections.emptyList());
	}
}