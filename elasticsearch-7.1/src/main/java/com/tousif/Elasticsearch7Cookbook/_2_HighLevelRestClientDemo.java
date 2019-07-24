package com.tousif.Elasticsearch7Cookbook;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class _2_HighLevelRestClientDemo {

	private static final Logger logger = LoggerFactory.getLogger(_2_HighLevelRestClientDemo.class);
	
	public static void main(String[] args) {

		try (RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));) {
			
			client.indices().refresh(new RefreshRequest("employee_esi"), RequestOptions.DEFAULT);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
