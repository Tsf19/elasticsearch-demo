/**
 * Class Name:			Constants
 * Created On:			10:51:20 pm, 15-Jan-2016
 *
 * Copyright (c) 2014 Cordis Technology. All rights reserved.
 *
 * Use is subject to license terms. 
 */

package com.tousif.temp;

public class Constants {
	
	/** ########## URLs ########## */
	public static final String URL_API_VERSION = "/v1.0";
	public static final String URL_API_BASE = "/api" + URL_API_VERSION;
	public static final String URL_API_UI_BASE = URL_API_BASE + "/ui";
	public static final String URL_API_UI_SEARCH = URL_API_UI_BASE + "/search";
	public static final String URL_API_GENERIC_SEARCH = URL_API_UI_BASE + "/generic-search";
	public static final String URL_API_CUSTOM_LINE_SEARCH = URL_API_UI_BASE + "/custom_line_search";
	public static final String URL_API_GENERIC_VARIABLE_VALUE_SEARCH = URL_API_UI_BASE + "/generic_variable_value_search";
	public static final String URL_API_CUSTOM_LINE_TABLE_SEARCH = URL_API_UI_BASE + "/custom_line_table_search";
	public static final String URL_API_STORE_SEARCH = URL_API_UI_BASE + "/store_search";

	/** ########## Operations ########## */
//	public static final String OPERATION_SEARCH_ALL = "/all";
	public static final String OPERATION_SEARCH_FILTER_BY_FIELDS = "/filter/fields";
	public static final String OPERATION_SEARCH_FILTER_BY_FIELDS_OPERATOR = OPERATION_SEARCH_FILTER_BY_FIELDS + "/operator";
	public static final String OPERATION_SEARCH_FIELD_LIST = "/field-list/{index}";
	
	public static final String OPERATION_SEARCH_ALL = "/search-all";
	public static final String OPERATION_SEARCH_BY_KEYWORD = "/search-by-keyword";
	public static final String OPERATION_LIST_ALL_FIELDS_AND_DATA_TYPES_OF_INDEX = "/list-all-fields-and-data-types-of-index";
	public static final String OPERATION_LIST_ALL_INDICES = "/list-all-indices";
	public static final String OPERATION_DELETE_INDICES = "/delete-index/{index}";
	
	
	public static final String OPERATION_SEARCH_BY_FILTER = "/search_by_filter";

	public static final String OPERATION_TAB_WEB_LIST = "/tab_web_list";;
	
	/** ########## Others ########## */
	public static final String SEARCH_RESULT_VO = "searchResultVo";
	public static final String RESPONSE_PAGE_NO = "pageNo";
	public static final String RESPONSE_PAGE_SIZE = "pageSize";
	public static final String RESPONSE_LIST_SIZE = "listSize";
	public static final String RESPONSE_PAGE_TOTAL = "pageTotal";
	public static final String RESPONSE_PAGING_ENABLED = "pagingEnabled";

	public static final String RESPONSE_ID = "id";
	public static final String RESPONSE_OBJECT = "object";
	public static final String RESPONSE_LIST = "list";
	public static final String RESPONSE_ACKNOWLEDGE = "acknowledge";

	public static final String SEARCH_HEADER_DELIMETER = " , ";
	public static final String SEARCH_HIGHLIGHTER_PRE_TAG = "<em>";
	public static final String SEARCH_HIGHLIGHTER_POST_TAG = "</em>";
	public static final String SEARCH_RESULT_MATCHED_FIELD_TEXT_MAP = "matchedFieldTextMap";
	public static final String SEARCH_RESULT_HEADER = "header";
	public static final String SEARCH_RESULT_SCORE = "score";
	public static final String SEARCH_MOTOR_INDEX_DMS = "motor_index_dms";

	
//	public static final String RESPONSE_STATUS = "status";
//	public static final String RESPONSE_STATUS_SUCCESS = "success";
//	public static final String RESPONSE_STATUS_ERROR = "error";
//	public static final String RESPONSE_MESSAGE = "message";
//	public static final String RESPONSE_EXCEPTION = "exception";
//	public static final String RESPONSE_ID_LIST = "idList";
	
	public static final String MESSAGE = "message";
	public static final String STATUS = "status";
	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_ERROR = "error";
	
	public static final String SEARCH_FIELDS_ALL = "_all";
	public static final String SEARCH_OPERATION_EXACT = "EXACT";
	public static final String SEARCH_OPERATION_LIKE = "LIKE";
	public static final String SEARCH_OPERATION_LTE = "LTE";
	public static final String SEARCH_OPERATION_GTE = "GTE";
	public static final String SEARCH_OPERATION_BETWEEN = "BETWEEN";



	/** ########## COMMON FIELD NAMES ########## */
	public static final String ID_FIELD_NAME = "id";
	public static final String PARENT_ID_FIELD_NAME = "parent_id";
	public static final String IS_LOWEST_FIELD_NAME = "is_lowest";
	public static final String ACTIVE_FIELD_NAME = "active";
	public static final String LEVEL_FIELD_NAME = "level";
	public static final String TIMESTAMP_FIELD_NAME = "@timestamp";
	public static final String VERSION_FIELD_NAME = "@version";

	/** ########## INDEX AND THEIR FIELD NAMES ########## */
	// cfg_generic_line_hierarchy
	public static final String CFG_GENERIC_LINE_HIERARCHY_INDEX_NAME = "cfg_generic_line_hierarchy";
	public static final String LINE_HIERARCHY_NAME_FIELD_NAME = "line_hierarachy_name";

	// cfg_company_variant_hierarchy
	public static final String CFG_COMPANY_VARIANT_LINE_HIERARCHY_INDEX_NAME = "cfg_company_variant_hierarchy";
	public static final String CATEGORY_NAME_FIELD_NAME = "category_name";

	// line & custom_line
	public static final String LINE_INDEX_NAME = "line";
	public static final String LOWEST_GENERIC_LINE_HIERARCHY_ID_FIELD_NAME = "lowest_generic_line_hierarchy_id";
	public static final String LOWEST_COMPANY_VARIANT_HIERARCHY_ID_FIELD_NAME = "lowest_company_variant_hierarchy_id";
	public static final String LINE_NAME_FIELD_NAME = "line_name";

	public static final String CUSTOM_LINE_INDEX_NAME = "custom_line";
	public static final String GENERIC_LINE_HIERARCHY_ID_FIELD_NAME = "generic_line_hierarchy_id";
	public static final String COMPANY_VARIANT_HIERARCHY_ID_FIELD_NAME = "company_variant_hierarchy_id";
	public static final String GENERIC_LINE_HIERARCHY_NAME_FIELD_NAME = "generic_line_hierarchy_name";
	public static final String COMPANY_VARIANT_HIERARCHY_NAME_FIELD_NAME = "company_variant_hierarchy_name";
	public static final String UNIVERSAL_LINE_NAME_FIELD_NAME = "universal_line_name";
	public static final String LINE_DESCRIPTION_FIELD_NAME = "line_description";
	public static final String LINE_AUDIANCE_FIELD_NAME = "line_audiance";
	public static final String LINE_TYPE_FIELD_NAME = "line_type";
	public static final String LINE_SELL_TYPE_FIELD_NAME = "line_sell_type";
	public static final String TRANSACTIONAL_FIELD_NAME = "transactional_type";
	public static final String PRODUCER_BUSINESS_ICON_FIELD_NAME = "producer_business_icon";

	// custom_line_extended_table
	public static final String CUSTOM_LINE_EXTENDED_TABLE_INDEX_NAME = "custom_line_extended_table";
	public static final String CUSTOM_LINE_ID_FIELD_NAME = "custom_line_id";
	public static final String TABLE_VARIABLE_NAME_FIELD_NAME = "table_variable_name";
	public static final String SINGLE_VARIABLE_NAME_FIELD_NAME = "single_variable_name";
	public static final String VALUE_FIELD_NAME = "value";
	
	// line_with_all_cords
	public static final String LINE_WITH_ALL_CORDS_INDEX_NAME = "line_with_all_cords";
	public static final String CC_ID_FIELD_NAME = "cc_id";
	public static final String TC_ID_FIELD_NAME = "tc_id";
	public static final String UC_ID_FIELD_NAME = "uc_id";
	public static final String CUSTOM_LINE_NAME_FIELD_NAME = "custom_line_name";
	public static final String UNIVERSAL_LINE_ID_FIELD_NAME = "universal_line_id";
	public static final String LINE_CATEGORY_FIELD_NAME = "line_category";
	public static final String LINE_HIERARCHY_FIELD_NAME = "line_hierarchy";
	public static final String PRODUCER_BUSINESS_ID_FIELD_NAME = "producer_business_id";
	public static final String PRODUCER_FRONT_NAME_FIELD_NAME = "producer_front_name";
	public static final String CUSTOM_LINE_BUSINESS_ID_FIELD_NAME = "custom_line_business_id";
	public static final String CUSTOM_LINE_FRONT_NAME_FIELD_NAME = "custom_line_front_name";
	public static final String CUSTOM_LINE_DESCRIPTION_FIELD_NAME = "custom_line_description";
	public static final String LINE_IMAGES_FIELD_NAME = "line_images";
	public static final String USER_ID_FIELD_NAME = "user_id";
	public static final String TRANSACTION_DATE_FIELD_NAME = "transaction_date";
	public static final String CC_DESCRIPTION_FIELD_NAME = "cc_description";
	public static final String TC_DESCRIPTION_FIELD_NAME = "tc_description";
	public static final String UC_DESCRIPTION_FIELD_NAME = "uc_description";
	public static final String CUSTOM_LINE_FX_DESCRIPTION_FIELD_NAME = "custom_line_fx_description";
	public static final String CC_FX_DESCRIPTION_FIELD_NAME = "cc_fx_description";
	public static final String TC_FX_DESCRIPTION_FIELD_NAME = "tc_fx_description";
	public static final String UC_FX_DESCRIPTION_FIELD_NAME = "uc_fx_description";


}
