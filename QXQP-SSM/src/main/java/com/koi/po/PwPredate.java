package com.koi.po;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;

/**
 * PwPredate entity. @author MyEclipse Persistence Tools
 */

public class PwPredate implements java.io.Serializable {

	// Fields

	private Long predateId;
	private Integer carderId;
	private String vehicleType;
	private Integer repairId;
	private String predateNum;
	private Timestamp openDate;
	private String maintenanceNum;
	private String carNum;
	private String owner;
	private String telephone;
	private String contacts;
	private String carMasterPhone;
	private String remark;
	private Boolean toAudit;
	private Double amount;
	private Double receivable;
	private String describe;
	private Boolean toTransferOrder;
	private Timestamp maintainData;
	private Double deserveMoney;
	private String customerNum;

	// Constructors

	/** default constructor */
	public PwPredate() {
	}

	/** minimal constructor */
	
	// Property accessors

	public Long getPredateId() {
		return this.predateId;
	}

	public PwPredate(Long predateId, Integer carderId, String vehicleType,
			Integer repairId, String predateNum, Timestamp openDate,
			String maintenanceNum, String carNum, String owner,
			String telephone, String contacts, String carMasterPhone,
			String remark, Boolean toAudit, Double amount, Double receivable,
			String describe, Boolean toTransferOrder, Timestamp maintainData,
			Double deserveMoney, String customerNum) {
		super();
		this.predateId = predateId;
		this.carderId = carderId;
		this.vehicleType = vehicleType;
		this.repairId = repairId;
		this.predateNum = predateNum;
		this.openDate = openDate;
		this.maintenanceNum = maintenanceNum;
		this.carNum = carNum;
		this.owner = owner;
		this.telephone = telephone;
		this.contacts = contacts;
		this.carMasterPhone = carMasterPhone;
		this.remark = remark;
		this.toAudit = toAudit;
		this.amount = amount;
		this.receivable = receivable;
		this.describe = describe;
		this.toTransferOrder = toTransferOrder;
		this.maintainData = maintainData;
		this.deserveMoney = deserveMoney;
		this.customerNum = customerNum;
	}

	public void setPredateId(Long predateId) {
		this.predateId = predateId;
	}

	public Integer getCarderId() {
		return this.carderId;
	}

	public void setCarderId(Integer carderId) {
		this.carderId = carderId;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}

	public String getPredateNum() {
		return this.predateNum;
	}

	public void setPredateNum(String predateNum) {
		this.predateNum = predateNum;
	}

	public Date getOpenDate() {
		if(openDate!=null) {
			return Util.strToUtilDate(this.openDate,"yyyy-MM-dd HH:mm:ss");
		}
		return null;
	
	}

	public void setOpenDate(Timestamp openDate) {
		this.openDate = openDate;
	}

	public String getMaintenanceNum() {
		return this.maintenanceNum;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public String getCarNum() {
		return this.carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getCarMasterPhone() {
		return this.carMasterPhone;
	}

	public void setCarMasterPhone(String carMasterPhone) {
		this.carMasterPhone = carMasterPhone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getToAudit() {
		return this.toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getReceivable() {
		return this.receivable;
	}

	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Boolean getToTransferOrder() {
		return this.toTransferOrder;
	}

	public void setToTransferOrder(Boolean toTransferOrder) {
		this.toTransferOrder = toTransferOrder;
	}

	public Date getMaintainData() {
		if(maintainData!=null) {
			return  Util.strToUtilDate(this.maintainData,"yyyy-MM-dd HH:mm:ss");
		}
		return null;
	}

	public void setMaintainData(Timestamp maintainData) {
		this.maintainData = maintainData;
	}

	public Double getDeserveMoney() {
		return this.deserveMoney;
	}

	public void setDeserveMoney(Double deserveMoney) {
		this.deserveMoney = deserveMoney;
	}

	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

}