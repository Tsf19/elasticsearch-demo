package com.tousif.service.impl;



import java.io.IOException;

import javax.naming.directory.SearchResult;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tousif.client.SearchClient;


@Component
public class SearchService {

	@Autowired
	private SearchClient searchClient;
	
	String myQueryString = "";

	MatchAllQueryBuilder qb = new MatchAllQueryBuilder();
	
	public SearchResponse searchAll() {

		RestHighLevelClient client = searchClient.getClient();
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 

		SearchRequest searchRequest = new SearchRequest(); 
		searchRequest.source(searchSourceBuilder);
		
		RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT;
		
		SearchResponse searchResponse = null;
		
		try {
			searchResponse = client.search(searchRequest, COMMON_OPTIONS);
			System.out.println("\n\n"+searchResponse+"\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;
		
//		CfgBusinessIndustry cfgBusinessIndustry = result.getFirstHit(CfgBusinessIndustry.class).source;
		
	}

	public SearchResponse searchAllWithinIndex(String index) {
		
		RestHighLevelClient client = searchClient.getClient();
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 

		SearchRequest searchRequest = new SearchRequest(); 
		searchRequest.source(searchSourceBuilder).indices(index);
		
		RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT;
		
		SearchResponse searchResponse = null;
		
		try {
			searchResponse = client.search(searchRequest, COMMON_OPTIONS);
			System.out.println("\n\n"+searchResponse+"\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;
	}

	public SearchResponse searchFieldInIndex(String index, String fieldName, String fieldValue) {
		RestHighLevelClient client = searchClient.getClient();
		
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, fieldValue);

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
//		searchSourceBuilder.query(QueryBuilders.existsQuery(field)); 
		searchSourceBuilder.query(matchQueryBuilder);
		
		SearchRequest searchRequest = new SearchRequest(); 
		searchRequest.source(searchSourceBuilder).indices(index);
		
		RequestOptions COMMON_OPTIONS = RequestOptions.DEFAULT;
		
		SearchResponse searchResponse = null;
		
		try {
			searchResponse = client.search(searchRequest, COMMON_OPTIONS);
			System.out.println("\n\n"+searchResponse+"\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;
	}
}
