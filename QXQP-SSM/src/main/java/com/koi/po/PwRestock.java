package com.koi.po;

/**
 * PwRestock entity. @author MyEclipse Persistence Tools
 */

public class PwRestock implements java.io.Serializable {

	// Fields

	private Integer restockId;
	private Integer receptionId;
	private String restockDate;
	private String operator;
	private Double amount;
	private String remark;
	private String restockMan;

	// Constructors

	/** default constructor */
	public PwRestock() {
	}

	/** full constructor */
	public PwRestock(Integer receptionId, String restockDate, String operator,
			Double amount, String remark, String restockMan) {
		this.receptionId = receptionId;
		this.restockDate = restockDate;
		this.operator = operator;
		this.amount = amount;
		this.remark = remark;
		this.restockMan = restockMan;
	}

	// Property accessors

	public Integer getRestockId() {
		return this.restockId;
	}

	public void setRestockId(Integer restockId) {
		this.restockId = restockId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public String getRestockDate() {
		return this.restockDate;
	}

	public void setRestockDate(String restockDate) {
		this.restockDate = restockDate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRestockMan() {
		return this.restockMan;
	}

	public void setRestockMan(String restockMan) {
		this.restockMan = restockMan;
	}

}