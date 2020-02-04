package com.tousif.temp;

import java.io.IOException;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tousif.client.SearchClient;
import com.tousif.model.SearchResultVo;

/**
 * @author DOMAIN\md.tousif
 * @date 12-Sep-2019 6:17:38 PM
 */
@Service
public class CustomLineTableWebListSearchServiceImpl implements CustomLineTableWebListSearchService {

	@Autowired
	private SearchClient searchClient;

	private static final Logger logger = LoggerFactory.getLogger(CustomLineTableWebListSearchServiceImpl.class);

	@Override
	public Map<String, Object> tabWebListSearch(TabInputVo tabInputVo, String loggedUserId) {

		Map<String, Object> responseMap = new HashMap<>();

		if (tabInputVo == null) {
			logger.info("input is null...");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,"input is null");
			return responseMap;
		}

		if (loggedUserId == null || loggedUserId.trim().isEmpty()) {
			logger.info("loggedUserId is null...");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,"loggedUserId is null");
			return responseMap;
		}

		Map<String, Object> expressionQueryResponseMap = tabWebListQueryBuilder(tabInputVo, loggedUserId);
		if(expressionQueryResponseMap.get(Constants.STATUS).equals(Constants.STATUS_ERROR)) {
			logger.info("no data found...");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,expressionQueryResponseMap.get(Constants.MESSAGE));
			return responseMap;
		}
		
		SearchResultVo lineWithAllCordsSearchResult = (SearchResultVo)expressionQueryResponseMap.get(Constants.SEARCH_RESULT_VO);
		List<Map<String, Object>> lineWithAllCordsList = lineWithAllCordsSearchResult.getSourceMap().get(Constants.LINE_WITH_ALL_CORDS_INDEX_NAME);
		
		Map<BigInteger, CordsOnCustomLineVo> cordsOnCustomLine = cordsOnCustomLine(lineWithAllCordsList); 		

		Map<BigInteger, CustomLineTableWebListSearchDisplayVo> customLineIdMapDisplayVo = new HashMap<>(); 
		
		for(Map<String, Object> lineWithAllCords : lineWithAllCordsList) {
		
			BigInteger customLineId = new BigInteger(lineWithAllCords.get(Constants.CUSTOM_LINE_ID_FIELD_NAME).toString());

			Long transactionDate = null;
			if(lineWithAllCords.get(Constants.TRANSACTION_DATE_FIELD_NAME) != null) {
				String transactionDateString = (String)lineWithAllCords.get(Constants.TRANSACTION_DATE_FIELD_NAME);
				transactionDate = ZonedDateTime.parse(transactionDateString).toEpochSecond() * 1000;
			}

			String customLineIconPathImages = lineWithAllCords.get(Constants.LINE_IMAGES_FIELD_NAME) != null
			? (String) lineWithAllCords.get(Constants.LINE_IMAGES_FIELD_NAME)
			: "";

			if(customLineIdMapDisplayVo.containsKey(customLineId)) {
				
				CustomLineTableWebListSearchDisplayVo displayVo = customLineIdMapDisplayVo.get(customLineId);
				
				if(displayVo.getTransactionDate() < transactionDate)
					displayVo.setTransactionDate(transactionDate);
				
				List<String> customLineIconPathImagesList = displayVo.getCustomLineIconPathImages();
				
				if(!customLineIconPathImagesList.contains(customLineIconPathImages)) {
					customLineIconPathImagesList.add(customLineIconPathImages);
					displayVo.setCustomLineIconPathImages(customLineIconPathImagesList);
				}
				
				customLineIdMapDisplayVo.put(customLineId, displayVo);
				
			}else {
				

				String customLineName = lineWithAllCords.get(Constants.CUSTOM_LINE_NAME_FIELD_NAME) != null
						? (String) lineWithAllCords.get(Constants.CUSTOM_LINE_NAME_FIELD_NAME)
						: null;

				BigInteger universalLineId = lineWithAllCords.get(Constants.UNIVERSAL_LINE_ID_FIELD_NAME) != null
						? new BigInteger(lineWithAllCords.get(Constants.UNIVERSAL_LINE_ID_FIELD_NAME).toString())
						: null;

				String universalLineName = lineWithAllCords.get(Constants.UNIVERSAL_LINE_NAME_FIELD_NAME) != null
						? (String) lineWithAllCords.get(Constants.UNIVERSAL_LINE_NAME_FIELD_NAME)
						: null;

				String customLineType = lineWithAllCords.get(Constants.LINE_TYPE_FIELD_NAME) != null
						? (String) lineWithAllCords.get(Constants.LINE_TYPE_FIELD_NAME)
						: null;

				BigInteger customLineBusinessId = lineWithAllCords
						.get(Constants.CUSTOM_LINE_BUSINESS_ID_FIELD_NAME) != null
								? new BigInteger(lineWithAllCords.get(Constants.CUSTOM_LINE_BUSINESS_ID_FIELD_NAME).toString())
								: null;

				BigInteger producerBusinessId = lineWithAllCords.get(Constants.PRODUCER_BUSINESS_ID_FIELD_NAME) != null
						? new BigInteger(lineWithAllCords.get(Constants.PRODUCER_BUSINESS_ID_FIELD_NAME).toString())
						: null;

				String producerFrontName = lineWithAllCords.get(Constants.PRODUCER_FRONT_NAME_FIELD_NAME) != null
						? (String) lineWithAllCords.get(Constants.PRODUCER_FRONT_NAME_FIELD_NAME)
						: null;
				
				List<String> customLineIconPathImagesList = new ArrayList<>();
				customLineIconPathImagesList.add(customLineIconPathImages);
				
				Map<String, Object> customLineWebDescription = lineWithAllCords.get(Constants.CUSTOM_LINE_DESCRIPTION_FIELD_NAME) != null
						? nestedJsonStringToNestedMapObject((String) lineWithAllCords.get(Constants.CUSTOM_LINE_DESCRIPTION_FIELD_NAME))
						: null;
						
				Map<String, Object>  totalCcWebDescription = lineWithAllCords.get(Constants.CC_DESCRIPTION_FIELD_NAME) != null
						? nestedJsonStringToNestedMapObject((String) lineWithAllCords.get(Constants.CC_DESCRIPTION_FIELD_NAME))
						: null;
						
				Map<String, Object> totalTcWebDescription = lineWithAllCords.get(Constants.TC_DESCRIPTION_FIELD_NAME) != null
						? nestedJsonStringToNestedMapObject((String) lineWithAllCords.get(Constants.TC_DESCRIPTION_FIELD_NAME))
						: null;
						
				Map<String, Object> totalUcWebDescription = lineWithAllCords.get(Constants.UC_DESCRIPTION_FIELD_NAME) != null
						? nestedJsonStringToNestedMapObject((String) lineWithAllCords.get(Constants.UC_DESCRIPTION_FIELD_NAME))
						: null;
				
				Integer ccCount = cordsOnCustomLine.get(customLineId).getCcSet().size();
				Integer tcCount = cordsOnCustomLine.get(customLineId).getTcSet().size();
				Integer ucCount = cordsOnCustomLine.get(customLineId).getUcSet().size();
				
				CustomLineTableWebListSearchDisplayVo displayVo = new CustomLineTableWebListSearchDisplayVo(
						customLineId, //customLineId
						customLineName, //customLineName
						universalLineId, //universalLineId
						universalLineName, //universalLineName
						customLineType, //customLineType
						customLineBusinessId, //customLineBusinessId
						producerBusinessId, //producerBusinessId
						producerFrontName, //producerFrontName
						customLineIconPathImagesList, //customLineIconPathImages
						customLineWebDescription, //customLineWebDescription
						totalCcWebDescription, //totalCcWebDescription
						totalTcWebDescription, //totalTcWebDescription
						totalUcWebDescription, //totalUcWebDescription
						ccCount, //ccCount
						tcCount, //tcCount
						ucCount, //ucCount
						transactionDate); //transactionDate
				
				customLineIdMapDisplayVo.put(customLineId, displayVo);
			}
		}
		
		List<CustomLineTableWebListSearchDisplayVo> customTabDisplayVoList = new ArrayList<>(customLineIdMapDisplayVo.values());
		
		Integer pageSize = tabInputVo.getPageSize() != null && tabInputVo.getPageSize() >= 1 
				? tabInputVo.getPageSize()
				: 50;
				
		Integer maxPageNumber = (int)Math.ceil(((double)customTabDisplayVoList.size()/pageSize));
				
		Integer pageNumber = tabInputVo.getPageNo() != null && tabInputVo.getPageNo() >= 1 
				? tabInputVo.getPageNo()
				: 1;
				
		if (pageNumber > maxPageNumber) {
			logger.info("maximum page number is "+ maxPageNumber +"...");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,"maximum page number is "+ maxPageNumber);
			return responseMap;
		}
				
		Integer startRange = pageSize * (pageNumber - 1);
		Integer endRange = (pageSize * pageNumber) > customTabDisplayVoList.size() ? customTabDisplayVoList.size() : pageSize * pageNumber;
		
		responseMap.put("customLineCount", customTabDisplayVoList.size());
		responseMap.put("list", customTabDisplayVoList.subList(startRange, endRange));
		
		return responseMap;
	}

	private Map<String, Object> tabWebListQueryBuilder(TabInputVo tabInputVo, String loggedUserId) {

		Map<String, Object> responseMap = new HashMap<>();

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		
//		boolQueryBuilder.must(QueryBuilders.matchQuery(Constants.USER_ID_FIELD_NAME, loggedUserId));
		
		if(tabInputVo.getUniversalLineCategory() != null && !tabInputVo.getUniversalLineCategory().trim().isEmpty()) {
			String universalLineCategory = tabInputVo.getUniversalLineCategory().trim();
			boolQueryBuilder.must(QueryBuilders.matchQuery(Constants.LINE_CATEGORY_FIELD_NAME, universalLineCategory));
		}
		
		if(tabInputVo.getCustomLineBusinessId() != null) {
			BigInteger customLineBusinessId = tabInputVo.getCustomLineBusinessId();
			boolQueryBuilder.must(QueryBuilders.matchQuery(Constants.CUSTOM_LINE_BUSINESS_ID_FIELD_NAME, customLineBusinessId));
		}
		
		if(tabInputVo.getProducerBusinessId() != null) {
			BigInteger producerBusinessId = tabInputVo.getProducerBusinessId();
			boolQueryBuilder.must(QueryBuilders.matchQuery(Constants.PRODUCER_BUSINESS_ID_FIELD_NAME, producerBusinessId));
		}
		
		if(tabInputVo.getCustomLineId() != null) {
			BigInteger customLineId = tabInputVo.getCustomLineId();
			boolQueryBuilder.must(QueryBuilders.matchQuery(Constants.CUSTOM_LINE_ID_FIELD_NAME, customLineId));
		}
		
		if(tabInputVo.getTcInitiatedDate() != null) {
			Long tcInitiatedDate = tabInputVo.getTcInitiatedDate();
			boolQueryBuilder.must(QueryBuilders.rangeQuery(Constants.TRANSACTION_DATE_FIELD_NAME).gte(tcInitiatedDate.toString()));
		}
		
		if(tabInputVo.getLineHierarchy() != null && !tabInputVo.getLineHierarchy().trim().isEmpty()) {
			BoolQueryBuilder searchLineHierarchyBoolQueryBuilder = QueryBuilders.boolQuery();			
			String lineHierarchy = tabInputVo.getLineHierarchy().trim();
			if(lineHierarchy.contains("-") || lineHierarchy.contains("#") || lineHierarchy.contains(" ")) {
				String[] lineHierarchyArray = lineHierarchy.split("\\-|\\#|\\ ");
				for(String lineHierarchyValue : lineHierarchyArray) {
					searchLineHierarchyBoolQueryBuilder.should(QueryBuilders.wildcardQuery(Constants.LINE_HIERARCHY_NAME_FIELD_NAME, lineHierarchyValue.toLowerCase()+"*"));
				}
				boolQueryBuilder.must(searchLineHierarchyBoolQueryBuilder);
			}
			else
				boolQueryBuilder.must(QueryBuilders.wildcardQuery(Constants.LINE_HIERARCHY_FIELD_NAME, lineHierarchy.toLowerCase()+"*"));
		}
		
		if(tabInputVo.getCustomLineName() != null && !tabInputVo.getCustomLineName().trim().isEmpty()) {
			BoolQueryBuilder searchCustomLineBoolQueryBuilder = QueryBuilders.boolQuery();
			String customLineName = tabInputVo.getCustomLineName().trim();
			if(customLineName.contains("-") || customLineName.contains("#") || customLineName.contains(" ")) {
				String[] customLineNameArray = customLineName.split("\\-|\\#|\\ ");
				for(String value : customLineNameArray) {
					searchCustomLineBoolQueryBuilder.should(QueryBuilders.wildcardQuery(Constants.CUSTOM_LINE_NAME_FIELD_NAME, value.toLowerCase()+"*"));
				}
				boolQueryBuilder.must(searchCustomLineBoolQueryBuilder);
			}
			else
				boolQueryBuilder.must(QueryBuilders.wildcardQuery(Constants.CUSTOM_LINE_NAME_FIELD_NAME, customLineName.toLowerCase()+"*"));
		}

		if(tabInputVo.getSearchKey() != null && !tabInputVo.getSearchKey().trim().isEmpty()) {
			
			String[] fieldsToSearch = new String[] {Constants.CUSTOM_LINE_NAME_FIELD_NAME, Constants.UNIVERSAL_LINE_NAME_FIELD_NAME,
													Constants.LINE_HIERARCHY_FIELD_NAME, Constants.PRODUCER_FRONT_NAME_FIELD_NAME};
			
			BoolQueryBuilder searchMultiFieldBoolQueryBuilder = QueryBuilders.boolQuery();
			String searchKey = tabInputVo.getSearchKey().trim();
			
			if(searchKey.contains("-") || searchKey.contains("#") || searchKey.contains(" ")) {
				String[] customLineNameArray = searchKey.split("\\-|\\#|\\ ");
				for(String value : customLineNameArray) {
					for(String field : fieldsToSearch) {
						searchMultiFieldBoolQueryBuilder.should(QueryBuilders.wildcardQuery(field, value.toLowerCase()+"*"));
					}
				}
			}
			else
				for(String field : fieldsToSearch) {
					searchMultiFieldBoolQueryBuilder.should(QueryBuilders.wildcardQuery(field, searchKey.toLowerCase()+"*"));
				}
			boolQueryBuilder.must(searchMultiFieldBoolQueryBuilder);
		}
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		
		String[] excludeFields = new String[] {Constants.TIMESTAMP_FIELD_NAME, Constants.VERSION_FIELD_NAME};
		String[] includeFields = null;
		
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.fetchSource(includeFields, excludeFields);

		searchSourceBuilder.sort(new FieldSortBuilder(Constants.ID_FIELD_NAME).order(SortOrder.ASC));
		
		SearchResultVo searchResultVo = queryExecutor(Constants.LINE_WITH_ALL_CORDS_INDEX_NAME, searchSourceBuilder);		
		
		if(searchResultVo == null || searchResultVo.getSourceMap().isEmpty()) {
			logger.info("No records found.......");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,"No records found");
			return responseMap;
		}
		responseMap.put(Constants.STATUS,Constants.STATUS_SUCCESS);
		responseMap.put(Constants.SEARCH_RESULT_VO, searchResultVo);
		return responseMap;
	}
	
	private Map<BigInteger, CordsOnCustomLineVo> cordsOnCustomLine(List<Map<String, Object>> lineWithAllCordsList) {

		Map<BigInteger, CordsOnCustomLineVo> cordsCountOnLineMap = new HashMap<>();
		
		for(Map<String, Object> lineWithAllCords : lineWithAllCordsList) {
			
			BigInteger customLineId = new BigInteger(lineWithAllCords.get(Constants.CUSTOM_LINE_ID_FIELD_NAME).toString());

			if(cordsCountOnLineMap.containsKey(customLineId)) {
				
				CordsOnCustomLineVo cordsOnCustomLineVo = cordsCountOnLineMap.get(customLineId);

				if(lineWithAllCords.get(Constants.CC_ID_FIELD_NAME) != null)
					cordsOnCustomLineVo.getCcSet().add((Integer)lineWithAllCords.get(Constants.CC_ID_FIELD_NAME));
				if(lineWithAllCords.get(Constants.TC_ID_FIELD_NAME) != null)
					cordsOnCustomLineVo.getTcSet().add((Integer)lineWithAllCords.get(Constants.TC_ID_FIELD_NAME));
				if(lineWithAllCords.get(Constants.UC_ID_FIELD_NAME) != null)
					cordsOnCustomLineVo.getUcSet().add((Integer)lineWithAllCords.get(Constants.UC_ID_FIELD_NAME));

				cordsCountOnLineMap.put(customLineId, cordsOnCustomLineVo);
				
			}else {
				
				CordsOnCustomLineVo cordsOnCustomLineVo = new CordsOnCustomLineVo();
				
				if (lineWithAllCords.get(Constants.CC_ID_FIELD_NAME) != null) {
					Set<Integer> ccSet = new HashSet<>();
					ccSet.add((Integer) lineWithAllCords.get(Constants.CC_ID_FIELD_NAME));
					cordsOnCustomLineVo.setCcSet(ccSet);
				}
				if (lineWithAllCords.get(Constants.TC_ID_FIELD_NAME) != null) {
					Set<Integer> tcSet = new HashSet<>();
					tcSet.add((Integer) lineWithAllCords.get(Constants.TC_ID_FIELD_NAME));
					cordsOnCustomLineVo.setTcSet(tcSet);
				}
				if (lineWithAllCords.get(Constants.UC_ID_FIELD_NAME) != null) {
					Set<Integer> ucSet = new HashSet<>();
					ucSet.add((Integer) lineWithAllCords.get(Constants.UC_ID_FIELD_NAME));
					cordsOnCustomLineVo.setUcSet(ucSet);
				}
				
				cordsCountOnLineMap.put(customLineId, cordsOnCustomLineVo);
			}
		}
		return cordsCountOnLineMap;
	}
	
	private Map<String, Object> nestedJsonStringToNestedMapObject(String jsonString) {
		Gson gson = new Gson();
		
		if(jsonString.startsWith("'") && jsonString.endsWith("'"))
			jsonString = jsonString.substring(1, jsonString.length()-1);
		
		HashMap<String, Object> mapObject = gson.fromJson(jsonString, HashMap.class);
		
		for(Entry<String, Object> nestedObject : mapObject.entrySet()) {
			String nestedObjectValue = nestedObject.getValue() != null ? nestedObject.getValue().toString() : "";
			if(nestedObjectValue.startsWith("{") && nestedObjectValue.endsWith("}")) {
				Map<String, Object> nestedJsonObject = nestedJsonStringToNestedMapObject(nestedObjectValue);
				mapObject.put(nestedObject.getKey(), nestedJsonObject);
			}
		}
		return mapObject;
	}
	
	private SearchResultVo queryExecutor(String indexName, SearchSourceBuilder searchSourceBuilder) {

		RestHighLevelClient client = searchClient.getClient();
		SearchRequest searchRequest = new SearchRequest();
		searchSourceBuilder.size(10000);
		searchRequest.indices(indexName).source(searchSourceBuilder);
		searchRequest.scroll(TimeValue.timeValueMinutes(1));

		SearchResponse searchResponse = null;
		try {
			logger.info("searching "+indexName+".......");
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String scrollId = searchResponse.getScrollId();
		SearchHits searchHits = searchResponse.getHits();
		Integer totalHits = (int)searchHits.getTotalHits().value;
		logger.info(indexName+"'s TotalHits: "+searchHits.getTotalHits()+".......");
		logger.info(indexName+"'s TimeTook: "+searchResponse.getTook()+".......");
		
		SearchHit[] hits = searchHits.getHits();
		
		Map<String, List<Map<String, Object>>>	sourceMap = new HashMap<>();
		
		while(hits != null && hits.length > 0){

			for(SearchHit hit : hits) {
				String index = hit.getIndex();

				Map<String, Object> sourceAsMap = hit.getSourceAsMap();
				
				if(sourceMap.containsKey(index)) {
					sourceMap.get(index).add(sourceAsMap);
				}else {
					List<Map<String, Object>> listOfMap = new ArrayList<>();					
					listOfMap.add(sourceAsMap);
					sourceMap.put(index, listOfMap);
				}
					
			}

			SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
			scrollRequest.scroll(TimeValue.timeValueSeconds(30));
			SearchResponse searchScrollResponse = null;
			try {
				searchScrollResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			scrollId = searchScrollResponse.getScrollId();
			hits = searchScrollResponse.getHits().getHits();
		}
		
		SearchResultVo searchResultVo = new SearchResultVo();
		searchResultVo.setSourceMap(sourceMap);
		searchResultVo.setTotalHits(totalHits);
		
		ClearScrollRequest clearScrollRequest = new ClearScrollRequest(); 
		clearScrollRequest.addScrollId(scrollId);
		ClearScrollResponse clearScrollResponse = null;
		try {
			clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean succeeded = clearScrollResponse.isSucceeded();
		logger.info("clearScrollResponse.isSucceeded(): "+succeeded+".......");
		return searchResultVo;
	}
	
	@Override
	public Map<String, Object> temp(TabInputVo tabInputVo, String loggedUserId) {

		Map<String, Object> responseMap = new HashMap<>();
		
		Long tcInitiatedDate = tabInputVo.getTcInitiatedDate();
		
		BoolQueryBuilder operationQueryBuilder = QueryBuilders.boolQuery();
		operationQueryBuilder
		.must(QueryBuilders
				.rangeQuery(Constants.TRANSACTION_DATE_FIELD_NAME)
				.gte(tcInitiatedDate.toString()));
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
		
		String[] excludeFields = new String[] {Constants.TIMESTAMP_FIELD_NAME, Constants.VERSION_FIELD_NAME};
		String[] includeFields = null;
		
		searchSourceBuilder.query(operationQueryBuilder);
		searchSourceBuilder.fetchSource(includeFields, excludeFields);

		searchSourceBuilder.sort(new FieldSortBuilder(Constants.ID_FIELD_NAME).order(SortOrder.ASC));
		
		SearchResultVo searchResultVo = queryExecutor(Constants.LINE_WITH_ALL_CORDS_INDEX_NAME, searchSourceBuilder);		
		
		if(searchResultVo.getSourceMap().isEmpty()) {
			logger.info("No records found.......");
			responseMap.put(Constants.STATUS,Constants.STATUS_ERROR);
			responseMap.put(Constants.MESSAGE,"No records found");
			return responseMap;
		}
		responseMap.put(Constants.STATUS,Constants.STATUS_SUCCESS);
		responseMap.put(Constants.SEARCH_RESULT_VO, searchResultVo);
		return responseMap;
	}

}
