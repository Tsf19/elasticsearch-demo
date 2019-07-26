package com.tousif.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SearchResultVo implements Serializable {

	private static final long serialVersionUID = -3578469685764542861L;
	private Integer totalHits;
	private Map<String, List<Map<String, Object>>> sourceMap;
	
	public SearchResultVo() {
		super();
	}

	public SearchResultVo(Integer totalHits, Map<String, List<Map<String, Object>>> sourceMap) {
		super();
		this.totalHits = totalHits;
		this.sourceMap = sourceMap;
	}

	public Integer getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}

	public Map<String, List<Map<String, Object>>> getSourceMap() {
		return sourceMap;
	}
	public void setSourceMap(Map<String, List<Map<String, Object>>> sourceMap) {
		this.sourceMap = sourceMap;
	}

	@Override
	public String toString() {
		return "SearchResultVo [totalHits=" + totalHits + ", sourceMap=" + sourceMap + "]";
	}
}
