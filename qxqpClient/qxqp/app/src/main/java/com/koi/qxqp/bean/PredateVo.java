package com.koi.qxqp.bean;

import java.sql.Timestamp;
import java.util.Date;

public class PredateVo implements java.io.Serializable{

	private Long predateId;
	private Integer carderId;
	private String vehicleType;
	private Integer repairId;
	private String predateNum;
	private Date openDate;
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
	private Date maintainData;
	private Double deserveMoney;
	private String customerNum;
	
	private String carderName;
	private String repairName;
	public Long getPredateId() {
		return predateId;
	}
	public void setPredateId(Long predateId) {
		this.predateId = predateId;
	}
	public Integer getCarderId() {
		return carderId;
	}
	public void setCarderId(Integer carderId) {
		this.carderId = carderId;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public Integer getRepairId() {
		return repairId;
	}
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	public String getPredateNum() {
		return predateNum;
	}
	public void setPredateNum(String predateNum) {
		this.predateNum = predateNum;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public String getMaintenanceNum() {
		return maintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getCarMasterPhone() {
		return carMasterPhone;
	}
	public void setCarMasterPhone(String carMasterPhone) {
		this.carMasterPhone = carMasterPhone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Boolean getToAudit() {
		return toAudit;
	}
	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getReceivable() {
		return receivable;
	}
	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Boolean getToTransferOrder() {
		return toTransferOrder;
	}
	public void setToTransferOrder(Boolean toTransferOrder) {
		this.toTransferOrder = toTransferOrder;
	}
	public Date getMaintainData() {
		return maintainData;
	}
	public void setMaintainData(Date maintainData) {
		this.maintainData = maintainData;
	}
	public Double getDeserveMoney() {
		return deserveMoney;
	}
	public void setDeserveMoney(Double deserveMoney) {
		this.deserveMoney = deserveMoney;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCarderName() {
		return carderName;
	}
	public void setCarderName(String carderName) {
		this.carderName = carderName;
	}
	public String getRepairName() {
		return repairName;
	}
	public void setRepairName(String repairName) {
		this.repairName = repairName;
	}
	
}
