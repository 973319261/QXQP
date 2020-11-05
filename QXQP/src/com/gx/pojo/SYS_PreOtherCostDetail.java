package com.gx.pojo;

import java.io.Serializable;

public class SYS_PreOtherCostDetail implements Serializable{
	private Integer PreOtherCostDetailID;
	private Integer PredateID;
	private Integer ExpensesID;
	private Double Amount;
	private Double Discount;
	private Double AmountPaid;
	private String Remark;
	public Integer getPreOtherCostDetailID() {
		return PreOtherCostDetailID;
	}
	public void setPreOtherCostDetailID(Integer preOtherCostDetailID) {
		PreOtherCostDetailID = preOtherCostDetailID;
	}
	public Integer getPredateID() {
		return PredateID;
	}
	public void setPredateID(Integer predateID) {
		PredateID = predateID;
	}
	public Integer getExpensesID() {
		return ExpensesID;
	}
	public void setExpensesID(Integer expensesID) {
		ExpensesID = expensesID;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Double getDiscount() {
		return Discount;
	}
	public void setDiscount(Double discount) {
		Discount = discount;
	}
	public Double getAmountPaid() {
		return AmountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		AmountPaid = amountPaid;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
}
