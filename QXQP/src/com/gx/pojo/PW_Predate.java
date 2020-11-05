package com.gx.pojo;

import java.io.Serializable;
import java.util.Date;

public class PW_Predate implements Serializable{
	private Integer PredateID;//预约维修ID
	private Integer CarderID;//接车人ID
	private Integer RepairID;//修理类别ID
	private String VehicleType;//车辆类型
	private String PredateNum;//预约单号
	private Date OpenDate;//开单日期
	private String MaintenanceNum;//维修单号
	private String CarNum;//车牌
	private String Owner;//车主
	private String Telephone;//联系电话
	private String Contacts;//联系人
	private String CarMasterPhone;//车主电话
	private String Remark;//备注
	private Boolean ToAudit;//审核否
	private Double Amount;//总金额
	private Double Receivable;//应收金额
	private String Describe;//描述
	private Boolean ToTransferOrder;//转单否
	private Date MaintainData;
	private String CustomerNum;//客户编码
	public Integer getPredateID() {
		return PredateID;
	}
	public void setPredateID(Integer predateID) {
		PredateID = predateID;
	}
	public Integer getCarderID() {
		return CarderID;
	}
	public void setCarderID(Integer carderID) {
		CarderID = carderID;
	}
	public Integer getRepairID() {
		return RepairID;
	}
	public void setRepairID(Integer repairID) {
		RepairID = repairID;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public String getPredateNum() {
		return PredateNum;
	}
	public void setPredateNum(String predateNum) {
		PredateNum = predateNum;
	}
	public Date getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(Date openDate) {
		OpenDate = openDate;
	}
	public String getMaintenanceNum() {
		return MaintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		MaintenanceNum = maintenanceNum;
	}
	public String getCarNum() {
		return CarNum;
	}
	public void setCarNum(String carNum) {
		CarNum = carNum;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public String getContacts() {
		return Contacts;
	}
	public void setContacts(String contacts) {
		Contacts = contacts;
	}
	public String getCarMasterPhone() {
		return CarMasterPhone;
	}
	public void setCarMasterPhone(String carMasterPhone) {
		CarMasterPhone = carMasterPhone;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Boolean getToAudit() {
		return ToAudit;
	}
	public void setToAudit(Boolean toAudit) {
		ToAudit = toAudit;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Double getReceivable() {
		return Receivable;
	}
	public void setReceivable(Double receivable) {
		Receivable = receivable;
	}
	public String getDescribe() {
		return Describe;
	}
	public void setDescribe(String describe) {
		Describe = describe;
	}
	public Boolean getToTransferOrder() {
		return ToTransferOrder;
	}
	public void setToTransferOrder(Boolean toTransferOrder) {
		ToTransferOrder = toTransferOrder;
	}
	public Date getMaintainData() {
		return MaintainData;
	}
	public void setMaintainData(Date maintainData) {
		MaintainData = maintainData;
	}
	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		CustomerNum = customerNum;
	}
}
