package com.gx.pojo;

import java.util.Date;

public class PW_Restock {
	 private Integer RestockID;
     private Integer ReceptionID;
     private Date RestockDate;
     private String Operator;
     private Double Amount;
     private String Remark;
     private String RestockMan;
	public Integer getRestockID() {
		return RestockID;
	}
	public void setRestockID(Integer restockID) {
		RestockID = restockID;
	}
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Date getRestockDate() {
		return RestockDate;
	}
	public void setRestockDate(Date restockDate) {
		RestockDate = restockDate;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getRestockMan() {
		return RestockMan;
	}
	public void setRestockMan(String restockMan) {
		RestockMan = restockMan;
	}
     
}
