package com.elasticsearch.jestclient;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.searchbox.annotations.JestId;

public class CfgBusinessIndustry implements Serializable{

	private static final long serialVersionUID = 905916804649181340L;

	@JestId
	private Integer id;
	private String businessIndustry;
	private String businessIndustryCode;
	private String businessIndustryDesc;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean active;
	private LocalDateTime created;
	private Long createdBy;
	
	
	public CfgBusinessIndustry() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getBusinessIndustry() {
		return businessIndustry;
	}
	public void setBusinessIndustry(String businessIndustry) {
		this.businessIndustry = businessIndustry;
	}

	public String getBusinessIndustryCode() {
		return businessIndustryCode;
	}
	public void setBusinessIndustryCode(String businessIndustryCode) {
		this.businessIndustryCode = businessIndustryCode;
	}

	public String getBusinessIndustryDesc() {
		return businessIndustryDesc;
	}
	public void setBusinessIndustryDesc(String businessIndustryDesc) {
		this.businessIndustryDesc = businessIndustryDesc;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}


	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "CfgBusinessIndustry [id=" + id + ", businessIndustry=" + businessIndustry + ", businessIndustryCode="
				+ businessIndustryCode + ", businessIndustryDesc=" + businessIndustryDesc + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", active=" + active + ", created=" + created + ", createdBy=" + createdBy
				+ "]";
	}
}
