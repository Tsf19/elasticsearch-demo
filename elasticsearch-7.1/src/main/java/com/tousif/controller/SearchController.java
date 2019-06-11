package com.tousif.controller;


import java.util.Locale;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tousif.service.impl.SearchService;

import org.springframework.http.MediaType;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

//	@RequestMapping(value = "/search-all" , method = RequestMethod.POST)
//	public @ResponseBody SearchResponse all(@RequestBody String index, Locale locale, Model model) {
//
//		SearchResponse searchResponse = searchService.searchAll(index);
//		return searchResponse;
//	}
	
	/**
	 * You can have multiple request mappings for a method. For that add one @RequestMapping annotation with a list of values.
	 **/
	@PostMapping(value = {"/search-all", "/search-everything", "/search-all*"})
	public SearchResponse searchAll(){
		SearchResponse searchResponse = searchService.searchAll();
		return searchResponse;
	}
	
	
	@PostMapping(value = "/search-index")
	public SearchResponse searchIndex(@RequestParam(value = "index", required = false) String index, Locale locale, Model model) {
		index = "vca_mdm_business_index";
		SearchResponse searchResponse = searchService.searchAllWithinIndex(index);
		return searchResponse;
	}
	
}
