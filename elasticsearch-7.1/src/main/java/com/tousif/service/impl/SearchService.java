package com.tousif.service.impl;



import java.io.IOException;

import javax.naming.directory.SearchResult;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
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
	
	/**
	 * Search queries are created using QueryBuilder objects. A QueryBuilder exists for every search query type 
	 * supported by Elasticsearch’s Query DSL.A QueryBuilder can be created using its constructor.
	 * Once created, the QueryBuilder object provides methods to configure the options of the search query it creates. 
	 */
	public SearchResponse searchFieldInIndexFuzziness(String index, String fieldName, String fieldValue) {
		RestHighLevelClient client = searchClient.getClient();
		
		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, fieldValue);
		matchQueryBuilder.fuzziness(Fuzziness.AUTO); 
		matchQueryBuilder.prefixLength(3); 
		matchQueryBuilder.maxExpansions(10);

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
	
	
	
	
	/**
	 * QueryBuilder objects can also be created using the QueryBuilders utility class. This class provides
	 * helper methods that can be used to create QueryBuilder objects using a fluent programming style.
	 */
	public SearchResponse searchFieldInIndexFuzziness2(String index, String fieldName, String fieldValue) {
		RestHighLevelClient client = searchClient.getClient();
		
//		MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, fieldValue);
//		matchQueryBuilder.fuzziness(Fuzziness.AUTO); 
//		matchQueryBuilder.prefixLength(3); 
//		matchQueryBuilder.maxExpansions(10);

		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(fieldName, fieldValue)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
		
		/**
		 * Whatever the method used to create it, the QueryBuilder object must be added to the SearchSourceBuilder as follows:
		 */
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
//		searchSourceBuilder.query(QueryBuilders.existsQuery(field)); 
		searchSourceBuilder.query(matchQueryBuilder);
		
		SearchRequest searchRequest = new SearchRequest(); 
		searchRequest.source(searchSourceBuilder).indices(index);
		

		/**
		 * @Source_filtering
		 * By default, search requests return the contents of the document _source but like in the Rest API you can overwrite this behavior.
		 * For example, you can turn off _source retrieval completely:
		 */
//		searchSourceBuilder.fetchSource(false);
		
		/**
		 * The method also accepts an array of one or more wildcard patterns to control which fields get included or excluded in a more fine grained way:
		 */
		String[] includeFields = new String[] {"country", "state", "cfg_*"};
//		String[] includeFields = null;
//		String[] excludeFields = new String[] {"section", "established_date"};
		String[] excludeFields = null;
		searchSourceBuilder.fetchSource(includeFields, excludeFields);
		
		/**
		 * @Specifying_Sorting
		 * The SearchSourceBuilder allows to add one or more SortBuilder instances. There are four special implementations
		 * (Field-, Score-, GeoDistance- and ScriptSortBuilder).
		 */
		searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));
		
		
		
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
	
	/**@Requesting_Highlighting
	 *
	 * Highlighting search results can be achieved by setting a HighlightBuilder on the SearchSourceBuilder.
	 * Different highlighting behaviour can be defined for each fields by adding one or more HighlightBuilder.
	 * Field instances to a HighlightBuilder.
	 */
	public SearchResponse RequestingHighlighting(String index, String fieldName, String fieldValue) {
		RestHighLevelClient client = searchClient.getClient();
		
		QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(fieldName, fieldValue)
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		searchSourceBuilder.query(matchQueryBuilder);
		
		SearchRequest searchRequest = new SearchRequest(); 
		searchRequest.source(searchSourceBuilder).indices(index);
		
//		searchSourceBuilder.fetchSource(false);
		searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));
		
		/**
		 * 1. Creates a new HighlightBuilder
		 * 2. Create a field highlighter for the title field
		 * 3. Set the field highlighter type
		 * 4. Add the field highlighter to the highlight builder
		 */
		HighlightBuilder highlightBuilder = new HighlightBuilder(); 
		
		HighlightBuilder.Field highlightState = new HighlightBuilder.Field("state");
		highlightState.highlighterType("unified");
		highlightBuilder.field(highlightState);  
		
		HighlightBuilder.Field highlightCountry = new HighlightBuilder.Field("country");
		highlightBuilder.field(highlightCountry);
		
		searchSourceBuilder.highlighter(highlightBuilder);
		
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
