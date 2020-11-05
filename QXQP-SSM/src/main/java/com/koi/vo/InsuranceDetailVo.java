package com.koi.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;

/**
 * SysInsuranceDetail entity. @author MyEclipse Persistence Tools
 */

public class InsuranceDetailVo implements java.io.Serializable {

	// Fields
	private Integer receptionId;
	private Integer documentStateId;
	private Integer balanceStateId;
	private String vehicleType;
	private String carder;
	private Integer repairId;
	private Integer customerSouId;
	private String carNum;
	private String mileage;
	private String owner;
	private String address;
	private String oilQuantity;
	private String ownerTele;
	private String engineNum;
	private String repairman;
	private String selfCoding;
	private String frameNum;
	private String repairmanTele;
	private Timestamp factoryDate;
	private Timestamp balanceDate;
	private Double amount;
	private Double amountPaid;
	private Boolean toAudit;
	private String completionDate;
	private String describe;
	private String maintenanceNum;
	private String collageState;
	private Boolean toSendWork;
	private Timestamp openDate;
	private Double maintenAmount;
	private Boolean toCompletion;
	private String customerNum;
	
	private String repairName;
	private String customerSou;
	private String documentState;
	private String balanceState;
	
	private Integer insuranceDetailId;
	private Integer insuranceComId;
	private String reportNum;
	private String policyNum;
	private Double policyMoney;
	private Double insuranceMoney;
	public InsuranceDetailVo() {
		// TODO Auto-generated constructor stub
	}
	public InsuranceDetailVo(Integer receptionId, Integer documentStateId,
			Integer balanceStateId, String vehicleType, String carder,
			Integer repairId, Integer customerSouId, String carNum,
			String mileage, String owner, String address, String oilQuantity,
			String ownerTele, String engineNum, String repairman,
			String selfCoding, String frameNum, String repairmanTele,
			Timestamp factoryDate, Timestamp balanceDate, Double amount,
			Double amountPaid, Boolean toAudit, String completionDate,
			String describe, String maintenanceNum, String collageState,
			Boolean toSendWork, Timestamp openDate, Double maintenAmount,
			Boolean toCompletion, String customerNum, String repairName,
			String customerSou, String documentState, String balanceState,
			Integer insuranceDetailId, Integer insuranceComId,
			String reportNum, String policyNum, Double policyMoney,
			Double insuranceMoney) {
		super();
		this.receptionId = receptionId;
		this.documentStateId = documentStateId;
		this.balanceStateId = balanceStateId;
		this.vehicleType = vehicleType;
		this.carder = carder;
		this.repairId = repairId;
		this.customerSouId = customerSouId;
		this.carNum = carNum;
		this.mileage = mileage;
		this.owner = owner;
		this.address = address;
		this.oilQuantity = oilQuantity;
		this.ownerTele = ownerTele;
		this.engineNum = engineNum;
		this.repairman = repairman;
		this.selfCoding = selfCoding;
		this.frameNum = frameNum;
		this.repairmanTele = repairmanTele;
		this.factoryDate = factoryDate;
		this.balanceDate = balanceDate;
		this.amount = amount;
		this.amountPaid = amountPaid;
		this.toAudit = toAudit;
		this.completionDate = completionDate;
		this.describe = describe;
		this.maintenanceNum = maintenanceNum;
		this.collageState = collageState;
		this.toSendWork = toSendWork;
		this.openDate = openDate;
		this.maintenAmount = maintenAmount;
		this.toCompletion = toCompletion;
		this.customerNum = customerNum;
		this.repairName = repairName;
		this.customerSou = customerSou;
		this.documentState = documentState;
		this.balanceState = balanceState;
		this.insuranceDetailId = insuranceDetailId;
		this.insuranceComId = insuranceComId;
		this.reportNum = reportNum;
		this.policyNum = policyNum;
		this.policyMoney = policyMoney;
		this.insuranceMoney = insuranceMoney;
	}
	public Integer getReceptionId() {
		return receptionId;
	}
	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}
	public Integer getDocumentStateId() {
		return documentStateId;
	}
	public void setDocumentStateId(Integer documentStateId) {
		this.documentStateId = documentStateId;
	}
	public Integer getBalanceStateId() {
		return balanceStateId;
	}
	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getCarder() {
		return carder;
	}
	public void setCarder(String carder) {
		this.carder = carder;
	}
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public Integer getCustomerSouId() {
		return customerSouId;
	}
	public void setCustomerSouId(Integer customerSouId) {
		this.customerSouId = customerSouId;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOilQuantity() {
		return oilQuantity;
	}
	public void setOilQuantity(String oilQuantity) {
		this.oilQuantity = oilQuantity;
	}
	public String getOwnerTele() {
		return ownerTele;
	}
	public void setOwnerTele(String ownerTele) {
		this.ownerTele = ownerTele;
	}
	public String getEngineNum() {
		return engineNum;
	}
	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}
	public String getRepairman() {
		return repairman;
	}
	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}
	public String getSelfCoding() {
		return selfCoding;
	}
	public void setSelfCoding(String selfCoding) {
		this.selfCoding = selfCoding;
	}
	public String getFrameNum() {
		return frameNum;
	}
	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}
	public String getRepairmanTele() {
		return repairmanTele;
	}
	public void setRepairmanTele(String repairmanTele) {
		this.repairmanTele = repairmanTele;
	}
	public Timestamp getFactoryDate() {
		return factoryDate;
	}
	public void setFactoryDate(Timestamp factoryDate) {
		this.factoryDate = factoryDate;
	}
	public Timestamp getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Timestamp balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Boolean getToAudit() {
		return toAudit;
	}
	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}
	public String getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getMaintenanceNum() {
		return maintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}
	public String getCollageState() {
		return collageState;
	}
	public void setCollageState(String collageState) {
		this.collageState = collageState;
	}
	public Boolean getToSendWork() {
		return toSendWork;
	}
	public void setToSendWork(Boolean toSendWork) {
		this.toSendWork = toSendWork;
	}
	public Date getOpenDate() {
		return Util.strToUtilDate(openDate, "yyyy-MM-dd hh:mm:ss");
	}
	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}
	public Double getMaintenAmount() {
		return maintenAmount;
	}
	public void setMaintenAmount(Double maintenAmount) {
		this.maintenAmount = maintenAmount;
	}
	public Boolean getToCompletion() {
		return toCompletion;
	}
	public void setToCompletion(Boolean toCompletion) {
		this.toCompletion = toCompletion;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	public String getCustomerSou() {
		return customerSou;
	}
	public void setCustomerSou(String customerSou) {
		this.customerSou = customerSou;
	}
	public String getDocumentState() {
		return documentState;
	}
	public void setDocumentState(String documentState) {
		this.documentState = documentState;
	}
	public String getBalanceState() {
		return balanceState;
	}
	public void setBalanceState(String balanceState) {
		this.balanceState = balanceState;
	}
	public Integer getInsuranceDetailId() {
		return insuranceDetailId;
	}
	public void setInsuranceDetailId(Integer insuranceDetailId) {
		this.insuranceDetailId = insuranceDetailId;
	}
	public Integer getInsuranceComId() {
		return insuranceComId;
	}
	public void setInsuranceComId(Integer insuranceComId) {
		this.insuranceComId = insuranceComId;
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
	public Double getInsuranceMoney() {
		return insuranceMoney;
	}
	public void setInsuranceMoney(Double insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}
	
}