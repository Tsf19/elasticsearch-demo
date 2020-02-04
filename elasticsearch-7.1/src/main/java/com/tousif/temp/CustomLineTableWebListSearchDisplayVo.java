package com.tousif.temp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author DOMAIN\md.tousif
 * @date 16-Sep-2019 3:15:17 PM
 */
public class CustomLineTableWebListSearchDisplayVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5898782436109577600L;
	private BigInteger customLineId;
	private String customLineName;
	private BigInteger universalLineId;
	private String universalLineName;
	private String customLineType;
	private BigInteger customLineBusinessId;
	private BigInteger producerBusinessId;
	private String producerFrontName;
	private List<String> customLineIconPathImages;
//	private List<VariableNameValueDataType> customLineWebDescription;
//	private List<VariableNameValueDataType> totalCcWebDescription;
//	private List<VariableNameValueDataType> totalTcWebDescription;
//	private List<VariableNameValueDataType> totalUcWebDescription;
	private Map<String, Object> customLineWebDescription;
	private Map<String, Object> totalCcWebDescription;
	private Map<String, Object> totalTcWebDescription;
	private Map<String, Object> totalUcWebDescription;
	private Integer ccCount;
	private Integer tcCount;
	private Integer ucCount;
	private Long transactionDate;

	public CustomLineTableWebListSearchDisplayVo() {
		super();
	}

	public CustomLineTableWebListSearchDisplayVo(BigInteger customLineId, String customLineName,
			BigInteger universalLineId, String universalLineName, String customLineType,
			BigInteger customLineBusinessId, BigInteger producerBusinessId, String producerFrontName,
			List<String> customLineIconPathImages, Map<String, Object> customLineWebDescription,
			Map<String, Object> totalCcWebDescription, Map<String, Object> totalTcWebDescription,
			Map<String, Object> totalUcWebDescription, Integer ccCount, Integer tcCount, Integer ucCount,
			Long transactionDate) {
		super();
		this.customLineId = customLineId;
		this.customLineName = customLineName;
		this.universalLineId = universalLineId;
		this.universalLineName = universalLineName;
		this.customLineType = customLineType;
		this.customLineBusinessId = customLineBusinessId;
		this.producerBusinessId = producerBusinessId;
		this.producerFrontName = producerFrontName;
		this.customLineIconPathImages = customLineIconPathImages;
		this.customLineWebDescription = customLineWebDescription;
		this.totalCcWebDescription = totalCcWebDescription;
		this.totalTcWebDescription = totalTcWebDescription;
		this.totalUcWebDescription = totalUcWebDescription;
		this.ccCount = ccCount;
		this.tcCount = tcCount;
		this.ucCount = ucCount;
		this.transactionDate = transactionDate;
	}

	public BigInteger getCustomLineId() {
		return customLineId;
	}

	public void setCustomLineId(BigInteger customLineId) {
		this.customLineId = customLineId;
	}

	public String getCustomLineName() {
		return customLineName;
	}

	public void setCustomLineName(String customLineName) {
		this.customLineName = customLineName;
	}

	public BigInteger getUniversalLineId() {
		return universalLineId;
	}

	public void setUniversalLineId(BigInteger universalLineId) {
		this.universalLineId = universalLineId;
	}

	public String getUniversalLineName() {
		return universalLineName;
	}

	public void setUniversalLineName(String universalLineName) {
		this.universalLineName = universalLineName;
	}

	public String getCustomLineType() {
		return customLineType;
	}

	public void setCustomLineType(String customLineType) {
		this.customLineType = customLineType;
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

	public String getProducerFrontName() {
		return producerFrontName;
	}

	public void setProducerFrontName(String producerFrontName) {
		this.producerFrontName = producerFrontName;
	}

	public List<String> getCustomLineIconPathImages() {
		return customLineIconPathImages;
	}

	public void setCustomLineIconPathImages(List<String> customLineIconPathImages) {
		this.customLineIconPathImages = customLineIconPathImages;
	}

	public Map<String, Object> getCustomLineWebDescription() {
		return customLineWebDescription;
	}

	public void setCustomLineWebDescription(Map<String, Object> customLineWebDescription) {
		this.customLineWebDescription = customLineWebDescription;
	}

	public Map<String, Object> getTotalCcWebDescription() {
		return totalCcWebDescription;
	}

	public void setTotalCcWebDescription(Map<String, Object> totalCcWebDescription) {
		this.totalCcWebDescription = totalCcWebDescription;
	}

	public Map<String, Object> getTotalTcWebDescription() {
		return totalTcWebDescription;
	}

	public void setTotalTcWebDescription(Map<String, Object> totalTcWebDescription) {
		this.totalTcWebDescription = totalTcWebDescription;
	}

	public Map<String, Object> getTotalUcWebDescription() {
		return totalUcWebDescription;
	}

	public void setTotalUcWebDescription(Map<String, Object> totalUcWebDescription) {
		this.totalUcWebDescription = totalUcWebDescription;
	}

	public Integer getCcCount() {
		return ccCount;
	}

	public void setCcCount(Integer ccCount) {
		this.ccCount = ccCount;
	}

	public Integer getTcCount() {
		return tcCount;
	}

	public void setTcCount(Integer tcCount) {
		this.tcCount = tcCount;
	}

	public Integer getUcCount() {
		return ucCount;
	}

	public void setUcCount(Integer ucCount) {
		this.ucCount = ucCount;
	}

	public Long getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Long transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "CustomLineTableWebListSearchDisplayVo [customLineId=" + customLineId + ", customLineName="
				+ customLineName + ", universalLineId=" + universalLineId + ", universalLineName=" + universalLineName
				+ ", customLineType=" + customLineType + ", customLineBusinessId=" + customLineBusinessId
				+ ", producerBusinessId=" + producerBusinessId + ", producerFrontName=" + producerFrontName
				+ ", customLineIconPathImages=" + customLineIconPathImages + ", customLineWebDescription="
				+ customLineWebDescription + ", totalCcWebDescription=" + totalCcWebDescription
				+ ", totalTcWebDescription=" + totalTcWebDescription + ", totalUcWebDescription="
				+ totalUcWebDescription + ", ccCount=" + ccCount + ", tcCount=" + tcCount + ", ucCount=" + ucCount
				+ ", transactionDate=" + transactionDate + "]";
	}

}
