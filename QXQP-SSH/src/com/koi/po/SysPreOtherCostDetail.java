package com.koi.po;

/**
 * SysPreOtherCostDetail entity. @author MyEclipse Persistence Tools
 */

public class SysPreOtherCostDetail implements java.io.Serializable {

	// Fields

	private Integer preOtherCostDetailId;
	private Integer predateId;
	private Integer expensesId;
	private Double amount;
	private Double discount;
	private Double amountPaid;
	private String remark;

	// Constructors

	/** default constructor */
	public SysPreOtherCostDetail() {
	}

	
	public SysPreOtherCostDetail(Integer preOtherCostDetailId,
			Integer predateId, Integer expensesId, Double amount,
			Double discount, Double amountPaid, String remark) {
		super();
		this.preOtherCostDetailId = preOtherCostDetailId;
		this.predateId = predateId;
		this.expensesId = expensesId;
		this.amount = amount;
		this.discount = discount;
		this.amountPaid = amountPaid;
		this.remark = remark;
	}


	// Property accessors

	public Integer getPreOtherCostDetailId() {
		return this.preOtherCostDetailId;
	}

	public void setPreOtherCostDetailId(Integer preOtherCostDetailId) {
		this.preOtherCostDetailId = preOtherCostDetailId;
	}

	public Integer getPredateId() {
		return this.predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getExpensesId() {
		return this.expensesId;
	}

	public void setExpensesId(Integer expensesId) {
		this.expensesId = expensesId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}