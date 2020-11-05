package com.koi.po;

/**
 * SysFittingsType entity. @author MyEclipse Persistence Tools
 */

public class SysFittingsType implements java.io.Serializable {

	// Fields

	private Integer fittingsTypeId;
	private String fittingsTypeName;
	private String fittingsTypeNum;
	private String remark;

	// Constructors

	/** default constructor */
	public SysFittingsType() {
	}

	/** minimal constructor */
	public SysFittingsType(String fittingsTypeName, String fittingsTypeNum) {
		this.fittingsTypeName = fittingsTypeName;
		this.fittingsTypeNum = fittingsTypeNum;
	}

	/** full constructor */
	public SysFittingsType(String fittingsTypeName, String fittingsTypeNum,
			String remark) {
		this.fittingsTypeName = fittingsTypeName;
		this.fittingsTypeNum = fittingsTypeNum;
		this.remark = remark;
	}

	// Property accessors

	public Integer getFittingsTypeId() {
		return this.fittingsTypeId;
	}

	public void setFittingsTypeId(Integer fittingsTypeId) {
		this.fittingsTypeId = fittingsTypeId;
	}

	public String getFittingsTypeName() {
		return this.fittingsTypeName;
	}

	public void setFittingsTypeName(String fittingsTypeName) {
		this.fittingsTypeName = fittingsTypeName;
	}

	public String getFittingsTypeNum() {
		return this.fittingsTypeNum;
	}

	public void setFittingsTypeNum(String fittingsTypeNum) {
		this.fittingsTypeNum = fittingsTypeNum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}