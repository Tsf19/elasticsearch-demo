package com.tousif.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

//@Configuration
//@EnableWebMvc
//@EnableElasticsearchRepositories(basePackages = "com.es")
public class SearchClient {

	private JestClient client;
	
	public SearchClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig
				.Builder("http://localhost:9200")
				.multiThreaded(true)
				.build());
		
		client = factory.getObject();
	}
	
	public JestClient getClient() {
				return client;
			}
}
