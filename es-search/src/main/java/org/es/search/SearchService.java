
package org.es.search;

import java.util.Set;

import org.es.search.vo.SearchRequestVo;
import org.es.search.vo.SearchResultVo;

public interface SearchService {

	/**
	 *
	 * @param searchRequestVo
	 * @return SearchResultVo
	 */
	public SearchResultVo getSearchResultByKeywordAndIndices(SearchRequestVo searchRequestVo);


	

}
