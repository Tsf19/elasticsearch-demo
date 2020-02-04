
package com.tousif.temp;

import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DOMAIN\md.tousif
 * @date 07-May-2019 11:12:03 AM
 */
//@Api(value = "ElasticSearch APIs")
@RestController
@RequestMapping(value = Constants.URL_API_CUSTOM_LINE_TABLE_SEARCH)
public class CustomLineTableWebListSearchController {

	@Autowired
	private CustomLineTableWebListSearchService customLineTableWebListSearchService;

	@PostMapping(value = Constants.OPERATION_TAB_WEB_LIST)
//	@ApiOperation (value = "",
//	notes = "")
	public Map<String, Object> tabWebListSearch(@RequestBody TabInputVo tabInputVo, Locale locale, Model model,
			Principal principal) {

		String loggedUserId = "100100";

		long start = System.currentTimeMillis();

		Map<String, Object> responseMap = customLineTableWebListSearchService.tabWebListSearch(tabInputVo,
				loggedUserId);

		if (responseMap.containsKey(Constants.STATUS) && responseMap.get(Constants.STATUS).equals(Constants.STATUS_ERROR))
			return responseMap;
		else {
			System.out.println("\n Latency of search " + (System.currentTimeMillis() - start) + "ms\n");
			return responseMap;
		}
	}

	@PostMapping(value = "/temp")
	public Map<String, Object> temp(@RequestBody TabInputVo tabInputVo, Locale locale, Model model, Principal principal) {

		String loggedUserId = "100100";

		long start = System.currentTimeMillis();

		Map<String, Object> responseMap = customLineTableWebListSearchService.temp(tabInputVo,
				loggedUserId);

		if (responseMap.get(Constants.STATUS).equals(Constants.STATUS_ERROR))
			return responseMap;
		else {
			System.out.println("\n Latency of search " + (System.currentTimeMillis() - start) + "ms\n");
			return responseMap;
		}
	}
	
}