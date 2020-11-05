package com.gx.pojo;

import java.util.Date;

public class PW_Insurance {
	 private Integer InsuranceID;
     private Integer InsuranceDetailID;
     private String InsuranceNum;
     private Date OpenDate;
     private Date BalanceDate;
     private String ClaimsStaff;
     private Boolean ToTicket;
     private String Remark;
     private Double Amount;
     private Boolean ToAudit;
	public Integer getInsuranceID() {
		return InsuranceID;
	}
	public void setInsuranceID(Integer insuranceID) {
		InsuranceID = insuranceID;
	}
	public Integer getInsuranceDetailID() {
		return InsuranceDetailID;
	}
	public void setInsuranceDetailID(Integer insuranceDetailID) {
		InsuranceDetailID = insuranceDetailID;
	}
	public String getInsuranceNum() {
		return InsuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		InsuranceNum = insuranceNum;
	}
	public Date getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(Date openDate) {
		OpenDate = openDate;
	}
	public Date getBalanceDate() {
		return BalanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		BalanceDate = balanceDate;
	}
	public String getClaimsStaff() {
		return ClaimsStaff;
	}
	public void setClaimsStaff(String claimsStaff) {
		ClaimsStaff = claimsStaff;
	}
	public Boolean getToTicket() {
		return ToTicket;
	}
	public void setToTicket(Boolean toTicket) {
		ToTicket = toTicket;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Boolean getToAudit() {
		return ToAudit;
	}
	public void setToAudit(Boolean toAudit) {
		ToAudit = toAudit;
	}
     
}
