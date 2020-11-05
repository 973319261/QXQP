package com.gx.pojo;

import java.util.Date;

public class PW_ThreePacks {
	 private Integer ThreePacksID;
     private Integer ThreePacksDetailID;
     private String InsuranceNum;
     private Date OpenDate;
     private Date BalanceDate;
     private String ClaimsStaff;
     private Boolean ToTicket;
     private String SelfCoding;
     private String Remark;
     private Double Amount;
     private Boolean ToAudit;
     private String Operator;
	public Integer getThreePacksID() {
		return ThreePacksID;
	}
	public void setThreePacksID(Integer threePacksID) {
		ThreePacksID = threePacksID;
	}
	public Integer getThreePacksDetailID() {
		return ThreePacksDetailID;
	}
	public void setThreePacksDetailID(Integer threePacksDetailID) {
		ThreePacksDetailID = threePacksDetailID;
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
	public String getSelfCoding() {
		return SelfCoding;
	}
	public void setSelfCoding(String selfCoding) {
		SelfCoding = selfCoding;
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
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
     
}
