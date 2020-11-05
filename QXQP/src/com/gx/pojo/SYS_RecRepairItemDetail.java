package com.gx.pojo;

import java.util.Date;

public class SYS_RecRepairItemDetail {
	 private Integer RecRepairItemDetailID;
	 private Integer RepairItemID;
     private Integer MaintainabilityID;
     private Integer ReceptionID;
     private Integer MaintenanceCrewID;
     private Integer RepairManID;
     private Double RepairCharge;
     private Double Discount;
     private Double AmountPaid;
     private String Remark;
     private Double JobHours;
     private Double JobAmount;
     private Date CompletionDate;
	public Integer getRepairItemID() {
		return RepairItemID;
	}
	public void setRepairItemID(Integer repairItemID) {
		RepairItemID = repairItemID;
	}
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	
	public Integer getMaintainabilityID() {
		return MaintainabilityID;
	}
	public void setMaintainabilityID(Integer maintainabilityID) {
		MaintainabilityID = maintainabilityID;
	}
	public Integer getMaintenanceCrewID() {
		return MaintenanceCrewID;
	}
	public void setMaintenanceCrewID(Integer maintenanceCrewID) {
		MaintenanceCrewID = maintenanceCrewID;
	}
	public Integer getRepairManID() {
		return RepairManID;
	}
	public void setRepairManID(Integer repairManID) {
		RepairManID = repairManID;
	}
	public Integer getRecRepairItemDetailID() {
		return RecRepairItemDetailID;
	}
	public void setRecRepairItemDetailID(Integer recRepairItemDetailID) {
		RecRepairItemDetailID = recRepairItemDetailID;
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
	public Double getJobHours() {
		return JobHours;
	}
	public void setJobHours(Double jobHours) {
		JobHours = jobHours;
	}
	public Double getJobAmount() {
		return JobAmount;
	}
	public void setJobAmount(Double jobAmount) {
		JobAmount = jobAmount;
	}
	public Date getCompletionDate() {
		return CompletionDate;
	}
	public void setCompletionDate(Date completionDate) {
		CompletionDate = completionDate;
	}
     
}
