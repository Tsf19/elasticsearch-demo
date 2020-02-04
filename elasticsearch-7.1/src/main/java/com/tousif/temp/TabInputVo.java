package com.tousif.temp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class TabInputVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4449270938615319204L;
	private Integer pageNo;
	private Integer pageSize;
	private String searchKey;
	private String customLineName;
	private List<String> lineTypeList;
	private Long tcInitiatedDate;
	private String universalLineCategory;
	private String lineHierarchy;
	private BigInteger customLineBusinessId;
	private BigInteger producerBusinessId;
	private BigInteger customLineId;
	private String cordType;
	private BigInteger cordId;

	public TabInputVo() {
		super();
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCustomLineName() {
		return customLineName;
	}

	public void setCustomLineName(String customLineName) {
		this.customLineName = customLineName;
	}

	public List<String> getLineTypeList() {
		return lineTypeList;
	}

	public void setLineTypeList(List<String> lineTypeList) {
		this.lineTypeList = lineTypeList;
	}

	public Long getTcInitiatedDate() {
		return tcInitiatedDate;
	}

	public void setTcInitiatedDate(Long tcInitiatedDate) {
		this.tcInitiatedDate = tcInitiatedDate;
	}

	public String getUniversalLineCategory() {
		return universalLineCategory;
	}

	public void setUniversalLineCategory(String universalLineCategory) {
		this.universalLineCategory = universalLineCategory;
	}

	public String getLineHierarchy() {
		return lineHierarchy;
	}

	public void setLineHierarchy(String lineHierarchy) {
		this.lineHierarchy = lineHierarchy;
	}

	public BigInteger getCustomLineBusinessId() {
		return customLineBusinessId;
	}

	public void setCustomLineBusinessId(BigInteger customLineBusinessId) {
		this.customLineBusinessId = customLineBusinessId;
	}

	public BigInteger getProducerBusinessId() {
		return producerBusinessId;
	}

	public void setProducerBusinessId(BigInteger producerBusinessId) {
		this.producerBusinessId = producerBusinessId;
	}

	public BigInteger getCustomLineId() {
		return customLineId;
	}

	public void setCustomLineId(BigInteger customLineId) {
		this.customLineId = customLineId;
	}

	public String getCordType() {
		return cordType;
	}

	public void setCordType(String cordType) {
		this.cordType = cordType;
	}

	public BigInteger getCordId() {
		return cordId;
	}

	public void setCordId(BigInteger cordId) {
		this.cordId = cordId;
	}

	@Override
	public String toString() {
		return "TabInputVo [pageNo=" + pageNo + ", pageSize=" + pageSize + ", searchKey=" + searchKey
				+ ", customLineName=" + customLineName + ", lineTypeList=" + lineTypeList + ", tcInitiatedDate="
				+ tcInitiatedDate + ", universalLineCategory=" + universalLineCategory + ", lineHierarchy="
				+ lineHierarchy + ", customLineBusinessId=" + customLineBusinessId + ", producerBusinessId="
				+ producerBusinessId + ", customLineId=" + customLineId + ", cordType=" + cordType + ", cordId="
				+ cordId + "]";
	}

}
