package com.tousif.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.xcontent.XContentHelper;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tousif.client.SearchClient;

@Component
public class ClusterService {

	@Autowired
	private SearchClient searchClient;
	
	public ClusterHealthStatus clusterHealth() {
		
		RestHighLevelClient client = searchClient.getClient();
		
		Request request = new Request("GET", "/_cluster/health");
		request.addParameter("wait_for_status", "green");

		Response response;
		ClusterHealthStatus healthStatus = null;
		try {
			response = client.getLowLevelClient().performRequest(request);
			InputStream is = response.getEntity().getContent();
			Map<String, Object> map = XContentHelper.convertToMap(XContentType.JSON.xContent(), is, true); 
			healthStatus = ClusterHealthStatus.fromString((String) map.get("status")); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return healthStatus;
	}
	
	public ClusterHealthResponse clusterInformation() {
		
		
		RestHighLevelClient client = searchClient.getClient();

		ClusterHealthRequest request = new ClusterHealthRequest();
		request.level(ClusterHealthRequest.Level.SHARDS);
		
		ClusterHealthResponse healthResponse = null;
		try {
			healthResponse = client.cluster().health(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return healthResponse;
	}
}
