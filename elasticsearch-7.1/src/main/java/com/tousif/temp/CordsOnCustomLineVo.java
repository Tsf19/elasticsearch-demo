package com.tousif.temp;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

/**
 * @author DOMAIN\md.tousif
 * @date 17-Sep-2019 2:44:19 PM
 */
public class CordsOnCustomLineVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<Integer> ccSet;
	private Set<Integer> tcSet;
	private Set<Integer> ucSet;

	public CordsOnCustomLineVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Set<Integer> getCcSet() {
		return ccSet;
	}

	public void setCcSet(Set<Integer> ccSet) {
		this.ccSet = ccSet;
	}

	public Set<Integer> getTcSet() {
		return tcSet;
	}

	public void setTcSet(Set<Integer> tcSet) {
		this.tcSet = tcSet;
	}

	public Set<Integer> getUcSet() {
		return ucSet;
	}

	public void setUcSet(Set<Integer> ucSet) {
		this.ucSet = ucSet;
	}

	@Override
	public String toString() {
		return "CordsOnCustomLineVo [ccSet=" + ccSet + ", tcSet=" + tcSet + ", ucSet=" + ucSet + "]";
	}

}
