package org.es.search;

import java.util.ArrayList;

import org.apache.http.HttpHost;
import org.apache.lucene.spatial.prefix.tree.GeohashPrefixTree.Factory;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.es")
public class SearchClient {


	//	private RestHighLevelClient client;
	//
	//	//	public SearchClient(Environment environment) {
	//	public SearchClient() {
	//
	//		client = new RestHighLevelClient(
	//				RestClient.builder(
	//						new HttpHost("localhost", 9200, "http")));
	//	}
	//
	//	
	//	public RestHighLevelClient getClient() {
	//		return client;
	//	}

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
