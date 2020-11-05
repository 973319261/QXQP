package com.koi.po;

/**
 * SysExpenses entity. @author MyEclipse Persistence Tools
 */

public class SysExpenses implements java.io.Serializable {

	// Fields

	private Integer expensesId;
	private String expensesName;
	private Double cost;
	private Double price;
	private String remark;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysExpenses() {
	}

	/** minimal constructor */
	public SysExpenses(String expensesName) {
		this.expensesName = expensesName;
	}

	/** full constructor */
	public SysExpenses(String expensesName, Double cost, Double price,
			String remark, Boolean toDeactivate) {
		this.expensesName = expensesName;
		this.cost = cost;
		this.price = price;
		this.remark = remark;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getExpensesId() {
		return this.expensesId;
	}

	public void setExpensesId(Integer expensesId) {
		this.expensesId = expensesId;
	}

	public String getExpensesName() {
		return this.expensesName;
	}

	public void setExpensesName(String expensesName) {
		this.expensesName = expensesName;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}