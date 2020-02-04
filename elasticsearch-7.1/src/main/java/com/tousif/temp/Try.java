package com.tousif.temp;

import java.util.HashMap;

import com.google.gson.Gson;

public class Try {

	public static void main(String[] args) {

		CustomLineContentToStoreInUsersActivityFeedVo vo;
		
		String jsonString = "{\n" + 
				"				\"customLineWebDescription\": [\n" + 
				"				{\n" + 
				"				\"variableName\": \"companyVariantHierarchyName\",\n" + 
				"				\"dataType\": \"String\",\n" + 
				"				\"value\": \"1.0\"\n" + 
				"				}\n" + 
				"				],\n" + 
				"				\"ccWebDescription\": null,\n" + 
				"				\"tcWebDescription\": null,\n" + 
				"				\"ucWebDescription\": null\n" + 
				"				}";
		
		Gson gson = new Gson();
		HashMap<String, Object> mapObject = gson.fromJson(jsonString, HashMap.class);
		
		System.out.println();
		
		CustomLineContentToStoreInUsersActivityFeedVo mapObject2 = gson.fromJson(jsonString, CustomLineContentToStoreInUsersActivityFeedVo.class);
		
		System.out.println();
		
        for(int i = 1; i <= 100; i++){
            System.out.println(i+",");    
        }
	}

}
