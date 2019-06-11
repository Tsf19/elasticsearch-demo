package com.tousif.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tousif.client.SearchClient;

@Configuration
public class ServiceContext {

	@Bean("searchClient")
	public SearchClient searchClient() {
		return new SearchClient();
	}
	
}
