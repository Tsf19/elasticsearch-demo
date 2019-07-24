package com.tousif.client;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
//@PropertySource({"file:${configDir}/searchconfig.properties"})
//@PropertySource("classpath:searchconfig.properties")
@PropertySource({"file:/home/local/DOMAIN/md.tousif/Documents/eclipse-workspace/elasticsearch-demo/elasticsearch-7.1/src/main/resources/searchconfig.properties"})
public class SearchClient {

//	@Autowired
//	public Environment env;
	
	/** METHOD-1*/
	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("searchconfig.properties");
	Properties properties = new Properties();
	
	String clusterNameKey1;
	String  clusterNameValue1;
	String  clusterServerHost1;
	Integer  clusterServerPort1;
	/** METHOD-1 -END*/	
	
	/** METHOD-2*/	
	@Value("${search.cluster.name.key}")
	String clusterNameKey2;
	
	@Value("${search.cluster.name.value}")
	String clusterNameValue2;
	
	@Value("${search.servers.url1}")
	String clusterServerHost2;

	@Value("${search.servers.port1}")
	Integer clusterServerPort2;
	/** METHOD-2 -END*/	
	
	/** METHOD-3*/
	String  clusterNameKey3;
	String  clusterNameValue3;
	String  clusterServerHost3;
	Integer  clusterServerPort3;
	/** METHOD-3 -END*/
	
	private RestHighLevelClient client;
	
	@SuppressWarnings("unused")
	public SearchClient(Environment env) {

		/** METHOD-1*/
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clusterNameKey1 = properties.getProperty("search.cluster.name.key");
		clusterNameValue1 =	properties.getProperty("search.cluster.name.value");
		clusterServerHost1 = properties.getProperty("search.servers.url1");
		clusterServerPort1 = Integer.parseInt(properties.getProperty("search.servers.port1"));
		/** METHOD-1 -END*/	
	
		/** METHOD-3*///
		clusterNameKey3 = env.getProperty("search.cluster.name.key");
		clusterNameValue3 =	env.getProperty("search.cluster.name.value");
		clusterServerHost3 = env.getProperty("search.servers.url1");
		clusterServerPort3 = Integer.parseInt(env.getProperty("search.servers.port1"));
		/** METHOD-3 -END*/
	
		
		client = new RestHighLevelClient(
				RestClient
				.builder(
						new HttpHost("localhost", 9200, "http")
						/*,new HttpHost("localhost", 9202, "http")
						,new HttpHost("localhost", 9203, "http")*/));
	}

	public RestHighLevelClient getClient() {//
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
