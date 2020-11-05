package com.koi.po;

/**
 * SysRepairMan entity. @author MyEclipse Persistence Tools
 */

public class SysRepairMan implements java.io.Serializable {

	// Fields

	private Integer repairManId;
	private String repairManName;
	private String repairManNum;
	private String remark;

	// Constructors

	/** default constructor */
	public SysRepairMan() {
	}

	/** minimal constructor */
	public SysRepairMan(String repairManName, String repairManNum) {
		this.repairManName = repairManName;
		this.repairManNum = repairManNum;
	}

	/** full constructor */
	public SysRepairMan(String repairManName, String repairManNum, String remark) {
		this.repairManName = repairManName;
		this.repairManNum = repairManNum;
		this.remark = remark;
	}

	// Property accessors

	public Integer getRepairManId() {
		return this.repairManId;
	}

	public void setRepairManId(Integer repairManId) {
		this.repairManId = repairManId;
	}

	public String getRepairManName() {
		return this.repairManName;
	}

	public void setRepairManName(String repairManName) {
		this.repairManName = repairManName;
	}

	public String getRepairManNum() {
		return this.repairManNum;
	}

	public void setRepairManNum(String repairManNum) {
		this.repairManNum = repairManNum;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}