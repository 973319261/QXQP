package com.gx.pojo;

public class SYS_InsuranceDetail {
	 private Integer InsuranceDetailID;
     private Integer ReceptionID;
     private Integer InsuranceComID;
     private String ReportNum;
     private String PolicyNum;
     private Double PolicyMoney;
     private Double InsuranceMoney;
	public Integer getInsuranceDetailID() {
		return InsuranceDetailID;
	}
	public void setInsuranceDetailID(Integer insuranceDetailID) {
		InsuranceDetailID = insuranceDetailID;
	}
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Integer getInsuranceComID() {
		return InsuranceComID;
	}
	public void setInsuranceComID(Integer insuranceComID) {
		InsuranceComID = insuranceComID;
	}
	public String getReportNum() {
		return ReportNum;
	}
	public void setReportNum(String reportNum) {
		ReportNum = reportNum;
	}
	public String getPolicyNum() {
		return PolicyNum;
	}
	public void setPolicyNum(String policyNum) {
		PolicyNum = policyNum;
	}
	public Double getPolicyMoney() {
		return PolicyMoney;
	}
	public void setPolicyMoney(Double policyMoney) {
		PolicyMoney = policyMoney;
	}
	public Double getInsuranceMoney() {
		return InsuranceMoney;
	}
	public void setInsuranceMoney(Double insuranceMoney) {
		InsuranceMoney = insuranceMoney;
	}
}
