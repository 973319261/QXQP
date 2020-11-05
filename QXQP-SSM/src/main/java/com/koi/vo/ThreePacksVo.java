package com.koi.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;


/**
 * PwThreePacks entity. @author MyEclipse Persistence Tools
 */

public class ThreePacksVo implements java.io.Serializable {

	// Fields

	private Integer threePacksId;
	private Integer threePacksDetailId;
	private String insuranceNum;
	private Timestamp openDate;
	private String balanceDate;
	private String claimsStaff;
	private Boolean toTicket;
	private String selfCoding;
	private String remark;
	private Double amount;
	private Boolean toAudit;
	private String operator;
	
	private String customerNum;
	private Integer claimComId;
	private Integer receptionId;
	private String carNum;
	private String vehicleType;
	private String maintenanceNum;
	private String owner;

	// Constructors

	/** default constructor */
	public ThreePacksVo() {
	}

	public ThreePacksVo(Integer threePacksId, Integer threePacksDetailId,
			String insuranceNum, Timestamp openDate, String balanceDate,
			String claimsStaff, Boolean toTicket, String selfCoding,
			String remark, Double amount, Boolean toAudit, String operator,
			String customerNum, Integer claimComId, Integer receptionId,
			String carNum, String vehicleType, String maintenanceNum,
			String owner) {
		super();
		this.threePacksId = threePacksId;
		this.threePacksDetailId = threePacksDetailId;
		this.insuranceNum = insuranceNum;
		this.openDate = openDate;
		this.balanceDate = balanceDate;
		this.claimsStaff = claimsStaff;
		this.toTicket = toTicket;
		this.selfCoding = selfCoding;
		this.remark = remark;
		this.amount = amount;
		this.toAudit = toAudit;
		this.operator = operator;
		this.customerNum = customerNum;
		this.claimComId = claimComId;
		this.receptionId = receptionId;
		this.carNum = carNum;
		this.vehicleType = vehicleType;
		this.maintenanceNum = maintenanceNum;
		this.owner = owner;
	}

	public Integer getThreePacksId() {
		return threePacksId;
	}

	public void setThreePacksId(Integer threePacksId) {
		this.threePacksId = threePacksId;
	}

	public Integer getThreePacksDetailId() {
		return threePacksDetailId;
	}

	public void setThreePacksDetailId(Integer threePacksDetailId) {
		this.threePacksDetailId = threePacksDetailId;
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

	public String getSelfCoding() {
		return selfCoding;
	}

	public void setSelfCoding(String selfCoding) {
		this.selfCoding = selfCoding;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public Integer getClaimComId() {
		return claimComId;
	}

	public void setClaimComId(Integer claimComId) {
		this.claimComId = claimComId;
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

	

}