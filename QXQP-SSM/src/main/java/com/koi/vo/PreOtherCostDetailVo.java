package com.koi.vo;

import java.io.Serializable;

public class PreOtherCostDetailVo implements Serializable{
	private Integer preOtherCostDetailId;
	private Integer predateId;
	private Integer expensesId;
	private Double amount;
	private Double discount;
	private Double amountPaid;
	private String remark;
	
	private String expensesName;

	public Integer getPreOtherCostDetailId() {
		return preOtherCostDetailId;
	}

	public void setPreOtherCostDetailId(Integer preOtherCostDetailId) {
		this.preOtherCostDetailId = preOtherCostDetailId;
	}

	public Integer getPredateId() {
		return predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getExpensesId() {
		return expensesId;
	}

	public void setExpensesId(Integer expensesId) {
		this.expensesId = expensesId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExpensesName() {
		return expensesName;
	}

	public void setExpensesName(String expensesName) {
		this.expensesName = expensesName;
	}
	
}
