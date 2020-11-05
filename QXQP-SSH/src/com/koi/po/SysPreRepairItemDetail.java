package com.koi.po;

/**
 * SysPreRepairItemDetail entity. @author MyEclipse Persistence Tools
 */

public class SysPreRepairItemDetail implements java.io.Serializable {

	// Fields

	private Integer preRepairItemDetailId;
	private Integer repairItemId;
	private Integer predateId;
	private Integer maintainabilityId;
	private Double repairCharge;
	private Double discount;
	private Double amountPaid;
	private String remark;

	// Constructors

	/** default constructor */
	public SysPreRepairItemDetail() {
	}


	public SysPreRepairItemDetail(Integer preRepairItemDetailId,
			Integer repairItemId, Integer predateId, Integer maintainabilityId,
			Double repairCharge, Double discount, Double amountPaid,
			String remark) {
		super();
		this.preRepairItemDetailId = preRepairItemDetailId;
		this.repairItemId = repairItemId;
		this.predateId = predateId;
		this.maintainabilityId = maintainabilityId;
		this.repairCharge = repairCharge;
		this.discount = discount;
		this.amountPaid = amountPaid;
		this.remark = remark;
	}


	// Property accessors

	public Integer getPreRepairItemDetailId() {
		return this.preRepairItemDetailId;
	}

	public void setPreRepairItemDetailId(Integer preRepairItemDetailId) {
		this.preRepairItemDetailId = preRepairItemDetailId;
	}

	public Integer getRepairItemId() {
		return this.repairItemId;
	}

	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
	}

	public Integer getPredateId() {
		return this.predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getMaintainabilityId() {
		return this.maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
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

}