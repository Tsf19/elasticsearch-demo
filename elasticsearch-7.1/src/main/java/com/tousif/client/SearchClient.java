package com.tousif.client;


import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

@Component
public class SearchClient {

	private RestHighLevelClient client;
	
	public SearchClient() {
		client = new RestHighLevelClient(
				RestClient
				.builder(
						new HttpHost("localhost", 9200, "http")
						,new HttpHost("localhost", 9202, "http")
						,new HttpHost("localhost", 9203, "http")));
	}
	
	public RestHighLevelClient getClient() {
				return client;
			}
	
	public void closeClient() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
