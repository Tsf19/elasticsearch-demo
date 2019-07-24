package com.tousif.Elasticsearch7Cookbook;

import org.elasticsearch.client.RestHighLevelClient;

public class _3_IndicesOperations {

	public static void main(String[] args) {
		RestHighLevelClientProvider clientProvider = new RestHighLevelClientProvider();
		RestHighLevelClient client = clientProvider.getClient();
	}
	
}
