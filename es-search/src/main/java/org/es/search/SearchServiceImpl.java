
package org.es.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.nio.entity.NStringEntity;
import org.apache.lucene.queryparser.flexible.core.builders.QueryBuilder;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchException;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.es.search.vo.SearchRequestVo;
import org.es.search.vo.SearchResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import com.carrotsearch.hppc.ObjectContainer;
import com.carrotsearch.hppc.cursors.ObjectCursor;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@Component
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchClient searchClient;

	private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

	@Override
	public SearchResultVo getSearchResultByKeywordAndIndices(SearchRequestVo searchRequestVo) {

//		RestHighLevelClient client = searchClient.getClient();
		JestClient client = searchClient.getClient();
		
		Map<String, List<Map<String, Object>>> sourceListMap = new HashMap<>();
		int totalHits = 0;

		try {
			
			Set<String> indices = searchRequestVo.getIndices();
			String[] indexes = indices.toArray(new String[0]);
			Object keyword = searchRequestVo.getKeyword();
			Integer startLimit = searchRequestVo.getStartLimit();
			Integer size = searchRequestVo.getSize();

			Set<String> fields = new HashSet<>();

			for (String index : indexes) {

//				SearchRequestBuilder prepareSearch = client.prepareSearch(index);
				
//				IndexRequest request = new IndexRequest(index).id("id");
//				IndexRequest request = new IndexRequest(index);
//				request.source("{\"field\":\"value\"}", XContentType.JSON);

				Search search = new Search
						.Builder("query")
						.addIndex(index)
						.build();
				
				SearchResult result = client.execute(search);

//				SearchRequest searchRequest = new SearchRequest(index);				
//				SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//			    QueryBuilder query = new QueryBuilders().termsQuery("name", "Neeraj");
//			    searchSourceBuilder.query(query)
//			    searchRequest.source(searchSourceBuilder)
			    
				
				 SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT)
				
				fields.addAll(getAllFieldsByIndex(index));

				String[] fieldsArray = fields.toArray(new String[fields.size()]);

				MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(keyword, fieldsArray).type(
						Type.PHRASE_PREFIX);
				prepareSearch.setQuery(builder.lenient(true));

				for (String field : fields) {
					prepareSearch.addHighlightedField(field);
					prepareSearch.addField(field);
				}

				if (startLimit != null)
					prepareSearch.setFrom(startLimit);
				if (size != null)
					prepareSearch.setSize(size);

				SearchResponse response = prepareSearch.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).setExplain(true)
						.execute().actionGet();

				SearchHits hits = response.getHits();
				for (SearchHit searchHit : hits) {
					// String index = searchHit.getIndex();
					Map<String, HighlightField> highlightFieldsMap = searchHit.getHighlightFields();

					Map<String, String> matchedFieldTextMap = new HashMap<>();
					StringBuffer rowHeader = new StringBuffer();

					for (String fieldName : highlightFieldsMap.keySet()) {
						HighlightField highlightField = highlightFieldsMap.get(fieldName);
						Text[] fragments = highlightField.getFragments();
						String fragmentText = fragments[0].string();
						matchedFieldTextMap.put(fieldName, fragmentText);
					}
					Map<String, SearchHitField> searchFieldsMap = searchHit.getFields();
					Map<String, Object> sourceMap = new HashMap<>();

					for (String field : searchFieldsMap.keySet()) {
						searchHitField searchHitField = searchFieldsMap.get(field);
						sourceMap.put(field, searchHitField.getValue());

					}

					sourceMap.remove("@version");
					sourceMap.remove("@timestamp");

					if (!sourceListMap.containsKey(index)) {
						List<Map<String, Object>> searchList = new ArrayList<>();

						sourceListMap.put(index, searchList);
					}

					sourceMap.put(Constants.SEARCH_RESULT_SCORE, searchHit.getScore());
					sourceMap.put(Constants.SEARCH_RESULT_MATCHED_FIELD_TEXT_MAP, matchedFieldTextMap);
					sourceMap.put(Constants.SEARCH_RESULT_HEADER, rowHeader.toString());

					List<Map<String, Object>> searchList = sourceListMap.get(index);

					
					/* code for motor index */
					
					if (index.equals(Constants.SEARCH_MOTOR_INDEX_DMS)) {
						if (sourceMap != null && sourceMap.containsKey("deleted")) {
							if (!sourceMap.get("deleted").equals("true")) {
								searchList.add(sourceMap);
							}
						}

					} else {
						searchList.add(sourceMap);
					}
					
					/* code for motor index */
					sourceListMap.put(index, searchList);
				}

				totalHits = hits.getHits().length;
			}
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			String exceptionClassName = SearchException.class.getName();
			String className = SearchServiceImpl.class.getName();
			ApplicationExceptionClassesVo applicationExceptionClassesVo = redisCacheService
					.getCachedRecordForApplicationExceptionClasses(exceptionClassName);
			String applicationMessage = applicationExceptionClassesVo != null ? applicationExceptionClassesVo
					.getSystemExceptionMessageNoTag() : null;
			String platformMessage = e.getMessage();
			throw new VcaSearchException(applicationMessage, platformMessage, null, className);
		}

		return new SearchResultVo(totalHits, sourceListMap);

	}

	@SuppressWarnings("unchecked")
//	@Override
	public Set<String> getAllFieldsByIndex(String index) {
		RestHighLevelClient client = searchClient.getClient();
		Set<String> fieldNames = new HashSet<>();
		try {
			GetMappingsRequest getMappingsRequest = new GetMappingsRequest();
			getMappingsRequest.indices(index);
			GetMappingsResponse mappingsResponse = client.admin().indices().getMappings(getMappingsRequest).actionGet();

			ObjectContainer<ImmutableOpenMap<String, MappingMetaData>> values = mappingsResponse.mappings().values();

			for (ObjectCursor<ImmutableOpenMap<String, MappingMetaData>> value : values) {
				ImmutableOpenMap<String, MappingMetaData> immutableOpenMap = value.value;

				for (ObjectCursor<String> key : immutableOpenMap.keys()) {
					Map<String, Object> propertiesMap;
					Map<String, Object> paramsMap = new HashMap<>();
					propertiesMap = immutableOpenMap.get(key.value).getSourceAsMap();

					for (String property : propertiesMap.keySet()) {
						paramsMap = (Map<String, Object>) propertiesMap.get(property);
						fieldNames.addAll(paramsMap.keySet());
					}

				}

			}
		}

		catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
			String exceptionClassName = SearchException.class.getName();
			String className = SearchServiceImpl.class.getName();
			ApplicationExceptionClassesVo applicationExceptionClassesVo = redisCacheService
					.getCachedRecordForApplicationExceptionClasses(exceptionClassName);
			String applicationMessage = applicationExceptionClassesVo != null ? applicationExceptionClassesVo
					.getSystemExceptionMessageNoTag() : null;
			String platformMessage = e.getMessage();
			throw new VcaSearchException(applicationMessage, platformMessage, null, className);
		}
		fieldNames.remove("@version");
		fieldNames.remove("@timestamp");

		return fieldNames;
	}
	
}
