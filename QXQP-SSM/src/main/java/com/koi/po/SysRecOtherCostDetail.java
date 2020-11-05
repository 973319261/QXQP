package com.koi.po;

/**
 * SysRecOtherCostDetail entity. @author MyEclipse Persistence Tools
 */

public class SysRecOtherCostDetail implements java.io.Serializable {

	// Fields

	private Integer recOtherCostDetailId;
	private Integer receptionId;
	private Integer expensesId;
	private Double amount;
	private Double discount;
	private Double amountPaid;
	private String remark;

	// Constructors

	/** default constructor */
	public SysRecOtherCostDetail() {
	}

	/** minimal constructor */
	public SysRecOtherCostDetail(Integer receptionId, Integer expensesId,
			Double amount, Double discount, Double amountPaid) {
		this.receptionId = receptionId;
		this.expensesId = expensesId;
		this.amount = amount;
		this.discount = discount;
		this.amountPaid = amountPaid;
	}

	/** full constructor */
	public SysRecOtherCostDetail(Integer receptionId, Integer expensesId,
			Double amount, Double discount, Double amountPaid, String remark) {
		this.receptionId = receptionId;
		this.expensesId = expensesId;
		this.amount = amount;
		this.discount = discount;
		this.amountPaid = amountPaid;
		this.remark = remark;
	}

	// Property accessors

	public Integer getRecOtherCostDetailId() {
		return this.recOtherCostDetailId;
	}

	public void setRecOtherCostDetailId(Integer recOtherCostDetailId) {
		this.recOtherCostDetailId = recOtherCostDetailId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
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