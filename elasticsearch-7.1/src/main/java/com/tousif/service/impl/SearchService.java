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
	
	public SearchResponse searchAll(String index) {

		@SuppressWarnings("unused")
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
}
