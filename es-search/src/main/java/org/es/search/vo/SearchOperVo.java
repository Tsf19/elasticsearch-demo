
package org.es.search.vo;

import java.io.Serializable;

public class SearchOperVo implements Serializable {

	private static final long	serialVersionUID	= 7371389065034134776L;

	private String				operation;
	private Object				lowValue;
	private Object				highValue;

	public SearchOperVo() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param operation
	 * @param lowValue
	 * @param highValue
	 */
	public SearchOperVo(String operation, Object lowValue, Object highValue) {

		this.operation = operation;
		this.lowValue = lowValue;
		this.highValue = highValue;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {

		return operation;
	}

	/**
	 * @param operation
	 *            the operation to set
	 */
	public void setOperation(String operation) {

		this.operation = operation;
	}

	/**
	 * @return the lowValue
	 */
	public Object getLowValue() {

		return lowValue;
	}

	/**
	 * @param lowValue
	 *            the lowValue to set
	 */
	public void setLowValue(Object lowValue) {

		this.lowValue = lowValue;
	}

	/**
	 * @return the highValue
	 */
	public Object getHighValue() {

		return highValue;
	}

	/**
	 * @param highValue
	 *            the highValue to set
	 */
	public void setHighValue(Object highValue) {

		this.highValue = highValue;
	}

}
