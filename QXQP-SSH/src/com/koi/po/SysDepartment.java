package com.koi.po;

/**
 * SysDepartment entity. @author MyEclipse Persistence Tools
 */

public class SysDepartment implements java.io.Serializable {

	// Fields

	private Integer departmentId;
	private String departmentName;
	private String departmentNun;

	// Constructors

	/** default constructor */
	public SysDepartment() {
	}

	/** full constructor */
	public SysDepartment(String departmentName, String departmentNun) {
		this.departmentName = departmentName;
		this.departmentNun = departmentNun;
	}

	// Property accessors

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentNun() {
		return this.departmentNun;
	}

	public void setDepartmentNun(String departmentNun) {
		this.departmentNun = departmentNun;
	}

}