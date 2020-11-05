package com.koi.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;


/**
 * PwThreePacks entity. @author MyEclipse Persistence Tools
 */

public class InsuranceVo implements java.io.Serializable {

	// Fields

	private Integer insuranceId;
	private Integer insuranceDetailId;
	private String insuranceNum;
	private Timestamp openDate;
	private String balanceDate;
	private String claimsStaff;
	private Boolean toTicket;
	private String remark;
	private Double amount;
	private Boolean toAudit;
	
	private String customerNum;
	private Integer receptionId;
	private String carNum;
	private String vehicleType;
	private String maintenanceNum;
	private String owner;
	private Integer insuranceComId;
	
	private Double insuranceMoney;
	private String reportNum;
	private String policyNum;
	private Double policyMoney;
	
	public InsuranceVo(){
		
	}
	
	public InsuranceVo(Integer insuranceId, Integer insuranceDetailId,
			String insuranceNum, Timestamp openDate, String balanceDate,
			String claimsStaff, Boolean toTicket, String remark, Double amount,
			Boolean toAudit, String customerNum, Integer receptionId,
			String carNum, String vehicleType, String maintenanceNum,
			String owner, Integer insuranceComId, Double insuranceMoney,
			String reportNum, String policyNum, Double policyMoney) {
		super();
		this.insuranceId = insuranceId;
		this.insuranceDetailId = insuranceDetailId;
		this.insuranceNum = insuranceNum;
		this.openDate = openDate;
		this.balanceDate = balanceDate;
		this.claimsStaff = claimsStaff;
		this.toTicket = toTicket;
		this.remark = remark;
		this.amount = amount;
		this.toAudit = toAudit;
		this.customerNum = customerNum;
		this.receptionId = receptionId;
		this.carNum = carNum;
		this.vehicleType = vehicleType;
		this.maintenanceNum = maintenanceNum;
		this.owner = owner;
		this.insuranceComId = insuranceComId;
		this.insuranceMoney = insuranceMoney;
		this.reportNum = reportNum;
		this.policyNum = policyNum;
		this.policyMoney = policyMoney;
	}

	public Integer getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}
	public Integer getInsuranceDetailId() {
		return insuranceDetailId;
	}
	public void setInsuranceDetailId(Integer insuranceDetailId) {
		this.insuranceDetailId = insuranceDetailId;
	}
	public String getInsuranceNum() {
		return insuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		this.insuranceNum = insuranceNum;
	}
	public Date getOpenDate() {
		return Util.strToUtilDate(openDate, "yyyy-MM-dd hh:mm:ss");
	}
	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}
	public String getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getClaimsStaff() {
		return claimsStaff;
	}
	public void setClaimsStaff(String claimsStaff) {
		this.claimsStaff = claimsStaff;
	}
	public Boolean getToTicket() {
		return toTicket;
	}
	public void setToTicket(Boolean toTicket) {
		this.toTicket = toTicket;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Boolean getToAudit() {
		return toAudit;
	}
	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public Integer getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getMaintenanceNum() {
		return maintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Double getInsuranceMoney() {
		return insuranceMoney;
	}
	public void setInsuranceMoney(Double insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	public String getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(String policyNum) {
		this.policyNum = policyNum;
	}
	public Double getPolicyMoney() {
		return policyMoney;
	}
	public void setPolicyMoney(Double policyMoney) {
		this.policyMoney = policyMoney;
	}

	public Integer getInsuranceComId() {
		return insuranceComId;
	}

	public void setInsuranceComId(Integer insuranceComId) {
		this.insuranceComId = insuranceComId;
	}
	
}