
package com.elasticsearch.jestclient;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

@Component
public class SearchServiceImpl {

	@Autowired
	private SearchClient searchClient;

	public void getSearch() {

		JestClient client = searchClient.getClient();
		
		Search search = new Search
				.Builder("query")
				.addIndex("vca_mdm_index")
				.build();
		
		SearchResult result = null;
		try {
			result = client.execute(search);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		JsonObject jsonObject = result.getJsonObject();
//		JsonObject hitsObj = jsonObject.getAsJsonObject("hits"); 
//		JsonArray hits = hitsObj.getAsJsonArray("hits");
		
		CfgBusinessIndustry cfgBusinessIndustry = result.getFirstHit(CfgBusinessIndustry.class).source;
		
	}
}
