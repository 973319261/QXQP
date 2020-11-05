package com.koi.po;

import java.sql.Date;


/**
 * SysRecRepairItemDetail entity. @author MyEclipse Persistence Tools
 */

public class SysRecRepairItemDetail implements java.io.Serializable {

	// Fields

	private Integer recRepairItemDetailId;
	private Integer repairItemId;
	private Integer maintainabilityId;
	private Integer receptionId;
	private Integer maintenanceCrewId;
	private Integer repairManId;
	private Double repairCharge;
	private Double discount;
	private Double amountPaid;
	private String remark;
	private Double jobHours;
	private Double jobAmount;
	private Date completionDate;

	
	// Constructors

	/** default constructor */
	public SysRecRepairItemDetail() {
	}

	

	public SysRecRepairItemDetail(Integer recRepairItemDetailId,
			Integer repairItemId, Integer maintainabilityId,
			Integer receptionId, Integer maintenanceCrewId,
			Integer repairManId, Double repairCharge, Double discount,
			Double amountPaid, String remark, Double jobHours,
			Double jobAmount, Date completionDate) {
		super();
		this.recRepairItemDetailId = recRepairItemDetailId;
		this.repairItemId = repairItemId;
		this.maintainabilityId = maintainabilityId;
		this.receptionId = receptionId;
		this.maintenanceCrewId = maintenanceCrewId;
		this.repairManId = repairManId;
		this.repairCharge = repairCharge;
		this.discount = discount;
		this.amountPaid = amountPaid;
		this.remark = remark;
		this.jobHours = jobHours;
		this.jobAmount = jobAmount;
		this.completionDate = completionDate;
	}



	// Property accessors

	public Integer getRecRepairItemDetailId() {
		return this.recRepairItemDetailId;
	}

	public void setRecRepairItemDetailId(Integer recRepairItemDetailId) {
		this.recRepairItemDetailId = recRepairItemDetailId;
	}

	public Integer getRepairItemId() {
		return this.repairItemId;
	}

	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
	}

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public Integer getMaintenanceCrewId() {
		return this.maintenanceCrewId;
	}

	public void setMaintenanceCrewId(Integer maintenanceCrewId) {
		this.maintenanceCrewId = maintenanceCrewId;
	}

	public Integer getRepairManId() {
		return this.repairManId;
	}

	public void setRepairManId(Integer repairManId) {
		this.repairManId = repairManId;
	}

	public Double getRepairCharge() {
		return this.repairCharge;
	}

	public void setRepairCharge(Double repairCharge) {
		this.repairCharge = repairCharge;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getJobHours() {
		return this.jobHours;
	}

	public void setJobHours(Double jobHours) {
		this.jobHours = jobHours;
	}

	public Double getJobAmount() {
		return this.jobAmount;
	}

	public void setJobAmount(Double jobAmount) {
		this.jobAmount = jobAmount;
	}

	public Date getCompletionDate() {
		return this.completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

}