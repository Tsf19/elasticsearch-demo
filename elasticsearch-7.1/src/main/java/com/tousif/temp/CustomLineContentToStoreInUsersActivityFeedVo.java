package com.tousif.temp;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author DOMAIN\md.tousif
 * @date 16-Jan-2020 6:50:56 PM
 */
public class CustomLineContentToStoreInUsersActivityFeedVo implements Serializable {

	private static final long serialVersionUID = -6969983836966442652L;
	private List<Map<String, Object>> customLineWebDescription;
	private List<Map<String, Object>> ccWebDescription;
	private List<Map<String, Object>> tcWebDescription;
	private List<Map<String, Object>> ucWebDescription;

	public CustomLineContentToStoreInUsersActivityFeedVo() {
		super();
	}

	public CustomLineContentToStoreInUsersActivityFeedVo(List<Map<String, Object>> customLineWebDescription,
			List<Map<String, Object>> ccWebDescription, List<Map<String, Object>> tcWebDescription,
			List<Map<String, Object>> ucWebDescription) {
		super();
		this.customLineWebDescription = customLineWebDescription;
		this.ccWebDescription = ccWebDescription;
		this.tcWebDescription = tcWebDescription;
		this.ucWebDescription = ucWebDescription;
	}

	public List<Map<String, Object>> getCustomLineWebDescription() {
		return customLineWebDescription;
	}

	public void setCustomLineWebDescription(List<Map<String, Object>> customLineWebDescription) {
		this.customLineWebDescription = customLineWebDescription;
	}

	public List<Map<String, Object>> getCcWebDescription() {
		return ccWebDescription;
	}

	public void setCcWebDescription(List<Map<String, Object>> ccWebDescription) {
		this.ccWebDescription = ccWebDescription;
	}

	public List<Map<String, Object>> getTcWebDescription() {
		return tcWebDescription;
	}

	public void setTcWebDescription(List<Map<String, Object>> tcWebDescription) {
		this.tcWebDescription = tcWebDescription;
	}

	public List<Map<String, Object>> getUcWebDescription() {
		return ucWebDescription;
	}

	public void setUcWebDescription(List<Map<String, Object>> ucWebDescription) {
		this.ucWebDescription = ucWebDescription;
	}

	@Override
	public String toString() {
		return "CustomLineContentToStoreInUsersActivityFeedVo [customLineWebDescription=" + customLineWebDescription
				+ ", ccWebDescription=" + ccWebDescription + ", tcWebDescription=" + tcWebDescription
				+ ", ucWebDescription=" + ucWebDescription + "]";
	}

}
