//package com.tousif.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
//import com.tousif.client.SearchClient;
//
//@Configuration
//public class ServiceContext {
//
//	@Autowired
//	private Environment env;
//
//	@Bean("searchClient")
//	public SearchClient searchClient() {
//		return new SearchClient(env);
//	}
//	
//}
