
package org.es.search.vo;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class SearchRequestVo implements Serializable {

	private static final long			serialVersionUID	= 4922450120498306516L;
	private Object						keyword;
	private Set<String>					indices;
	private Integer						startLimit;
	private Integer						size;
	private Map<String, Set<String>>	indicesParamMap;
	private Map<String, SearchOperVo>	paramMap;

	public SearchRequestVo() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param keyword
	 * @param indices
	 * @param startLimit
	 * @param size
	 */
	public SearchRequestVo(Object keyword, Set<String> indices, Integer startLimit, Integer size) {

		this.keyword = keyword;
		this.indices = indices;
		this.startLimit = startLimit;
		this.size = size;
	}

	/**
	 * @return the indicesParamMap
	 */
	public Map<String, Set<String>> getIndicesParamMap() {

		return indicesParamMap;
	}

	/**
	 * @param indicesParamMap
	 *            the indicesParamMap to set
	 */
	public void setIndicesParamMap(Map<String, Set<String>> indicesParamMap) {

		this.indicesParamMap = indicesParamMap;
	}

	/**
	 * @return the paramMap
	 */
	public Map<String, SearchOperVo> getParamMap() {

		return paramMap;
	}

	/**
	 * @param paramMap
	 *            the paramMap to set
	 */
	public void setParamMap(Map<String, SearchOperVo> paramMap) {

		this.paramMap = paramMap;
	}

	/**
	 * @return the keyword
	 */
	public Object getKeyword() {

		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(Object keyword) {

		this.keyword = keyword;
	}

	/**
	 * @return the indices
	 */
	public Set<String> getIndices() {

		return indices;
	}

	/**
	 * @param indices
	 *            the indices to set
	 */
	public void setIndices(Set<String> indices) {

		this.indices = indices;
	}

	/**
	 * @return the startLimit
	 */
	public Integer getStartLimit() {

		return startLimit;
	}

	/**
	 * @param startLimit
	 *            the startLimit to set
	 */
	public void setStartLimit(Integer startLimit) {

		this.startLimit = startLimit;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {

		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Integer size) {

		this.size = size;
	}

}
