package com.tousif.Elasticsearch7Cookbook;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestHighLevelClientProvider {

	private static final Logger logger = LoggerFactory.getLogger(RestHighLevelClientProvider.class);

	RestHighLevelClient client;

	public RestHighLevelClientProvider() {
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		logger.info("client created...");
	}

	public RestHighLevelClient getClient() {
		if (client != null) {
			logger.info("client created successfully...");
		} else {
			logger.error("client creation failed...");
		}
		return client;
	}

	public void closeClient() {
		try {
			logger.info("closing the client...");
			client.close();
			logger.info("client closed...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
