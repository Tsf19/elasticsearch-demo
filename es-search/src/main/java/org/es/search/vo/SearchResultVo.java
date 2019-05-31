
package org.es.search.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SearchResultVo implements Serializable {

	private static final long						serialVersionUID	= 2612657792751254908L;
	private int										totalHits;
	private Map<String, List<Map<String, Object>>>	sourceMap;

	public SearchResultVo() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param scrollId
	 * @param totalHits
	 * @param sourceList
	 */
	public SearchResultVo(int totalHits, Map<String, List<Map<String, Object>>> sourceMap) {

		this.totalHits = totalHits;
		this.sourceMap = sourceMap;
	}

	/**
	 * @return the totalHits
	 */
	public int getTotalHits() {

		return totalHits;
	}

	/**
	 * @param totalHits
	 *            the totalHits to set
	 */
	public void setTotalHits(int totalHits) {

		this.totalHits = totalHits;
	}

	/**
	 * @return the sourceList
	 */
	public Map<String, List<Map<String, Object>>> getSourceMap() {

		return sourceMap;
	}

	/**
	 * @param sourceList
	 *            the sourceList to set
	 */
	public void setSourceMap(Map<String, List<Map<String, Object>>> sourceMap) {

		this.sourceMap = sourceMap;
	}

}
