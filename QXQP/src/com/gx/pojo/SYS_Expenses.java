package com.gx.pojo;

public class SYS_Expenses {
	private Integer ExpensesID;
	private String ExpensesName;
	private String Remark;
	private Double Cost;
	private Double Price;
	private Boolean ToDeactivate;
	public Integer getExpensesID() {
		return ExpensesID;
	}
	public void setExpensesID(Integer expensesID) {
		ExpensesID = expensesID;
	}
	public String getExpensesName() {
		return ExpensesName;
	}
	public void setExpensesName(String expensesName) {
		ExpensesName = expensesName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Double getCost() {
		return Cost;
	}
	public void setCost(Double cost) {
		Cost = cost;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public Boolean getToDeactivate() {
		return ToDeactivate;
	}
	public void setToDeactivate(Boolean toDeactivate) {
		ToDeactivate = toDeactivate;
	}
	
}
