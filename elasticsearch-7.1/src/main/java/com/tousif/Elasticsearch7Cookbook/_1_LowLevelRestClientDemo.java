package com.tousif.Elasticsearch7Cookbook;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class _1_LowLevelRestClientDemo {

	public static void main(String[] args) {
		
		RestClient client = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
		///employee_esi/_search
		
		Request request = new Request("GET", "/employee_esi/_search");
		
		try {
			Response response = client.performRequest(request);

			if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + response.getStatusLine());
			}else {
				HttpEntity entity = response.getEntity();
				String responseBody = EntityUtils.toString(entity);
				System.out.println(responseBody);
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
