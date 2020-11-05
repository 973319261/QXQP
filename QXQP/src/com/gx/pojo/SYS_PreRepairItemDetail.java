package com.gx.pojo;

import java.io.Serializable;

public class SYS_PreRepairItemDetail implements Serializable{
	private Integer PreRepairItemDetailID;
	private Integer RepairItemID;
	private Integer PredateID;
	private Integer MaintainabilityID;
	private Double RepairCharge;
	private Double Discount;
	private Double AmountPaid;
	private String Remark;
	public Integer getPreRepairItemDetailID() {
		return PreRepairItemDetailID;
	}
	public void setPreRepairItemDetailID(Integer preRepairItemDetailID) {
		PreRepairItemDetailID = preRepairItemDetailID;
	}
	public Integer getRepairItemID() {
		return RepairItemID;
	}
	public void setRepairItemID(Integer repairItemID) {
		RepairItemID = repairItemID;
	}
	public Integer getPredateID() {
		return PredateID;
	}
	public void setPredateID(Integer predateID) {
		PredateID = predateID;
	}
	public Integer getMaintainabilityID() {
		return MaintainabilityID;
	}
	public void setMaintainabilityID(Integer maintainabilityID) {
		MaintainabilityID = maintainabilityID;
	}
	public Double getRepairCharge() {
		return RepairCharge;
	}
	public void setRepairCharge(Double repairCharge) {
		RepairCharge = repairCharge;
	}
	public Double getDiscount() {
		return Discount;
	}
	public void setDiscount(Double discount) {
		Discount = discount;
	}
	public Double getAmountPaid() {
		return AmountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		AmountPaid = amountPaid;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
}
