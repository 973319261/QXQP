package com.gx.pojo;

public class SYS_RecOtherCostDetail {
	 private Integer RecOtherCostDetailID;
	 private Integer ReceptionID;
     private Integer ExpensesID;
     private Double Amount;
     private Double Discount;
     private Double AmountPaid;
     private String Remark;
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Integer getExpensesID() {
		return ExpensesID;
	}
	public void setExpensesID(Integer expensesID) {
		ExpensesID = expensesID;
	}
	public Integer getRecOtherCostDetailID() {
		return RecOtherCostDetailID;
	}
	public void setRecOtherCostDetailID(Integer recOtherCostDetailID) {
		RecOtherCostDetailID = recOtherCostDetailID;
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
