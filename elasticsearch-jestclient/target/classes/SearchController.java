package com.elasticsearch.jestclient;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@RestController
@RequestMapping//("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping(value = "/search-all" , method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> all(@RequestBody String index, Locale locale, Model model) {

		searchService.searchAll(index);
		return null;
	}
	
}
