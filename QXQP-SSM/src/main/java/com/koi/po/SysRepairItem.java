package com.koi.po;

/**
 * SysRepairItem entity. @author MyEclipse Persistence Tools
 */

public class SysRepairItem implements java.io.Serializable {

	// Fields

	private Integer repairItemId;
	private String repairItemName;
	private Integer maintenanceId;
	private Integer repairClassId;
	private String repairItemNum;
	private String pinYinCode;
	private Double repairCharge;
	private Double timeUnit;
	private Double workTime;
	private String remark;
	private Boolean toDeactivate;
	private Double money;

	// Constructors

	/** default constructor */
	public SysRepairItem() {
	}

	/** minimal constructor */
	public SysRepairItem(Integer maintenanceId, Integer repairClassId,
			String repairItemNum) {
		this.maintenanceId = maintenanceId;
		this.repairClassId = repairClassId;
		this.repairItemNum = repairItemNum;
	}

	/** full constructor */
	public SysRepairItem(String repairItemName, Integer maintenanceId,
			Integer repairClassId, String repairItemNum, String pinYinCode,
			Double repairCharge, Double timeUnit, Double workTime,
			String remark, Boolean toDeactivate, Double money) {
		this.repairItemName = repairItemName;
		this.maintenanceId = maintenanceId;
		this.repairClassId = repairClassId;
		this.repairItemNum = repairItemNum;
		this.pinYinCode = pinYinCode;
		this.repairCharge = repairCharge;
		this.timeUnit = timeUnit;
		this.workTime = workTime;
		this.remark = remark;
		this.toDeactivate = toDeactivate;
		this.money = money;
	}

	// Property accessors

	public Integer getRepairItemId() {
		return this.repairItemId;
	}

	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
	}

	public String getRepairItemName() {
		return this.repairItemName;
	}

	public void setRepairItemName(String repairItemName) {
		this.repairItemName = repairItemName;
	}

	public Integer getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public Integer getRepairClassId() {
		return this.repairClassId;
	}

	public void setRepairClassId(Integer repairClassId) {
		this.repairClassId = repairClassId;
	}

	public String getRepairItemNum() {
		return this.repairItemNum;
	}

	public void setRepairItemNum(String repairItemNum) {
		this.repairItemNum = repairItemNum;
	}

	public String getPinYinCode() {
		return this.pinYinCode;
	}

	public void setPinYinCode(String pinYinCode) {
		this.pinYinCode = pinYinCode;
	}

	public Double getRepairCharge() {
		return this.repairCharge;
	}

	public void setRepairCharge(Double repairCharge) {
		this.repairCharge = repairCharge;
	}

	public Double getTimeUnit() {
		return this.timeUnit;
	}

	public void setTimeUnit(Double timeUnit) {
		this.timeUnit = timeUnit;
	}

	public Double getWorkTime() {
		return this.workTime;
	}

	public void setWorkTime(Double workTime) {
		this.workTime = workTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}