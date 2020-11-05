package com.koi.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;

/**
 * PwReception entity. @author MyEclipse Persistence Tools
 */

public class RestockVo implements java.io.Serializable {

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
	
	private Integer collageId;
	private String StrAudit;
	public RestockVo() {
		// TODO Auto-generated constructor stub
	}
	

	public RestockVo(Integer receptionId, Integer documentStateId,
			Integer balanceStateId, String vehicleType, String carder,
			Integer repairId, Integer customerSouId, String carNum,
			String mileage, String owner, String address, String oilQuantity,
			String ownerTele, String engineNum, String repairman,
			String selfCoding, String frameNum, String repairmanTele,
			Timestamp factoryDate, Timestamp balanceDate, Double amount,
			Double amountPaid, Boolean toAudit, String completionDate,
			String describe, String maintenanceNum, String collageState,
			Boolean toSendWork, Timestamp openDate, Double maintenAmount,
			Boolean toCompletion, String customerNum, Integer collageId) {
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
		this.collageId = collageId;
	}


	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public Integer getDocumentStateId() {
		return this.documentStateId;
	}

	public void setDocumentStateId(Integer documentStateId) {
		this.documentStateId = documentStateId;
	}

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCarder() {
		return this.carder;
	}

	public void setCarder(String carder) {
		this.carder = carder;
	}

	public Integer getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public Integer getCustomerSouId() {
		return this.customerSouId;
	}

	public void setCustomerSouId(Integer customerSouId) {
		this.customerSouId = customerSouId;
	}

	public String getCarNum() {
		return this.carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getMileage() {
		return this.mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOilQuantity() {
		return this.oilQuantity;
	}

	public void setOilQuantity(String oilQuantity) {
		this.oilQuantity = oilQuantity;
	}

	public String getOwnerTele() {
		return this.ownerTele;
	}

	public void setOwnerTele(String ownerTele) {
		this.ownerTele = ownerTele;
	}

	public String getEngineNum() {
		return this.engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getRepairman() {
		return this.repairman;
	}

	public void setRepairman(String repairman) {
		this.repairman = repairman;
	}

	public String getSelfCoding() {
		return this.selfCoding;
	}

	public void setSelfCoding(String selfCoding) {
		this.selfCoding = selfCoding;
	}

	public String getFrameNum() {
		return this.frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}

	public String getRepairmanTele() {
		return this.repairmanTele;
	}

	public void setRepairmanTele(String repairmanTele) {
		this.repairmanTele = repairmanTele;
	}

	public Date getFactoryDate() {
		return Util.strToUtilDate(this.factoryDate,"yyyy-MM-dd HH:mm:ss");
	}

	public void setFactoryDate(Timestamp factoryDate) {
		this.factoryDate = factoryDate;
	}

	public Date getBalanceDate() {
		return Util.strToUtilDate(this.balanceDate,"yyyy-MM-dd HH:mm:ss");
	}

	public void setBalanceDate(Timestamp balanceDate) {
		this.balanceDate = balanceDate;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public String getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMaintenanceNum() {
		return this.maintenanceNum;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public String getCollageState() {
		return this.collageState;
	}

	public void setCollageState(String collageState) {
		this.collageState = collageState;
	}

	public Boolean getToSendWork() {
		return this.toSendWork;
	}

	public void setToSendWork(Boolean toSendWork) {
		this.toSendWork = toSendWork;
	}

	public Date getOpenDate() {
		return  Util.strToUtilDate(this.openDate,"yyyy-MM-dd HH:mm:ss");
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public Double getMaintenAmount() {
		return this.maintenAmount;
	}

	public void setMaintenAmount(Double maintenAmount) {
		this.maintenAmount = maintenAmount;
	}

	public Boolean getToCompletion() {
		return this.toCompletion;
	}

	public void setToCompletion(Boolean toCompletion) {
		this.toCompletion = toCompletion;
	}

	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}


	public Integer getCollageId() {
		return collageId;
	}


	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
	}


	public String getStrAudit() {
		return StrAudit;
	}


	public void setStrAudit(String strAudit) {
		StrAudit = strAudit;
	}
	

}