package com.tousif.temp;

import java.util.Map;

/**
 * @author DOMAIN\md.tousif
 * @date 12-Sep-2019 6:17:31 PM
 */
public interface CustomLineTableWebListSearchService {

	Map<String, Object> tabWebListSearch(TabInputVo tabInputVo, String loggedUserId);

	Map<String, Object> temp(TabInputVo tabInputVo, String loggedUserId);

}