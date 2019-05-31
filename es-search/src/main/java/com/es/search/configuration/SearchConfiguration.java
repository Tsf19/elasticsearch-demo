package com.es.search.configuration;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Setting;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.es")
public class SearchConfiguration {

//	NodeBuilder nodeBuilder() {
//		return new NodeBuilder();
//	}
	
//	Client client2;

//	private TransportClient client;
	private RestHighLevelClient client;
	
//	@SuppressWarnings({ "deprecation", "resource", "unused" })
	public SearchConfiguration(Environment environment) {
		
//		String clusterNameKey =	environment.getProperty("search.cluster.name.key");
//		String clusterNameValue = environment.getProperty("search.cluster.name.value");
//		String clusterServerHost = environment.getProperty("search.servers.url1");
//		int clusterServerPort = Integer.parseInt(environment.getProperty("search.servers.port1"));
//		
//		Settings settings = Settings.builder().put(clusterNameKey, clusterNameValue).build();
//		try {
//			TransportClient client = new PreBuiltTransportClient(settings)
//				    .addTransportAddress(
//				    		new TransportAddress(
//				    				InetAddress.getByName(clusterServerHost), clusterServerPort));
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
		
	        client = new RestHighLevelClient(
	                RestClient.builder(
	                	    new HttpHost("localhost", 9200, "http")));
		
		
	}
	
//    @Bean(destroyMethod = "close")
//    public RestHighLevelClient client() {
//    	
//    	
//
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                		new HttpHost(elasticsearchHost)));
//
//        return client;
//    }
	
//	
//	ElasticsearchOperations elasticsearchOperations() {
//		
//		File tempFile = null;
//		
//		try {
//			tempFile = File.createTempFile("temp-elastic", Long.toString(System.nanoTime()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("\n\ntemp-elastic --------------------->" + tempFile.getAbsolutePath());
//	
//		Settings.Builder elasticSearchSettings = Settings.builder()
//				.put("http.enabled", "true")
//				.put("index.number_of_shards", "1")
//				.put("path.data", new File(tempFile, "data").getAbsolutePath())
//				.put("path.logs", new File(tempFile, "logs").getAbsolutePath())
//				.put("path.work", new File(tempFile, "work").getAbsolutePath())
//				.put("path.home", tempFile);
//		
//		return new ElasticsearchTemplate(client);
//		
//	}
    
//    RestClient restClient = RestClient.builder(
//    		new HttpHost("localhost", 9200, "http")
//    		).build();
    
    
	public RestHighLevelClient getClient() {
		return client;
	}
}
