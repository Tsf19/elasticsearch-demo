
package com.tousif.service.impl;

import java.io.IOException;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tousif.client.SearchClient;
import com.tousif.model.CfgBusinessIndustry;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@Component
public class SearchService {

	@Autowired
	private SearchClient searchClient;

	public void searchAll(String index) {

		JestClient client = searchClient.getClient();
		
		
		Search search = new Search
				.Builder("{" + 
						"  \"query\": {" + 
						"    \"match\": {" + 
						"      \"businessIndustry\" : \"Education\"" + 
						"    }" + 
						"  }" + 
						"}")
				.addIndex("vca_mdm_index")
				.addType("cfg_business_industry_document_type")
				.build();
		
		SearchResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CfgBusinessIndustry cfgBusinessIndustry = result.getFirstHit(CfgBusinessIndustry.class).source;
		
	}
}
