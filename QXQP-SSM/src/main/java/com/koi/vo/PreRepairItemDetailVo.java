package com.koi.vo;

public class PreRepairItemDetailVo implements java.io.Serializable {
	private Integer preRepairItemDetailId;
	private Integer repairItemId;
	private Integer predateId;
	private Integer maintainabilityId;
	private Double repairCharge;
	private Double discount;
	private Double amountPaid;
	private String remark;
	
	private String repairItemName;
	private String maintainabilityName;
	private String maintenanceName;
	public Integer getPreRepairItemDetailId() {
		return preRepairItemDetailId;
	}
	public void setPreRepairItemDetailId(Integer preRepairItemDetailId) {
		this.preRepairItemDetailId = preRepairItemDetailId;
	}
	public Integer getRepairItemId() {
		return repairItemId;
	}
	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
	}
	public Integer getPredateId() {
		return predateId;
	}
	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}
	public Integer getMaintainabilityId() {
		return maintainabilityId;
	}
	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}
	public Double getRepairCharge() {
		return repairCharge;
	}
	public void setRepairCharge(Double repairCharge) {
		this.repairCharge = repairCharge;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRepairItemName() {
		return repairItemName;
	}
	public void setRepairItemName(String repairItemName) {
		this.repairItemName = repairItemName;
	}
	public String getMaintainabilityName() {
		return maintainabilityName;
	}
	public void setMaintainabilityName(String maintainabilityName) {
		this.maintainabilityName = maintainabilityName;
	}
	public String getMaintenanceName() {
		return maintenanceName;
	}
	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}
	
}
