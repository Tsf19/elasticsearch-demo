package com.tousif.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.tousif.client.SearchClient;

//Instead of servlateName-servlet.xml
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tousif")
//@Import({SearchClient.class, /*ServiceContext.class*/})
public class AppConfig {

}