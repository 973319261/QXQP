package com.koi.po;

/**
 * PwUser entity. @author MyEclipse Persistence Tools
 */

public class PwUser implements java.io.Serializable {

	// Fields

	private Integer userId;
	private Integer userTypeId;
	private Integer departmentId;
	private String userNum;
	private String userName;
	private String password;
	private String remark;
	private Boolean toUse;

	// Constructors

	/** default constructor */
	public PwUser() {
	}

	/** minimal constructor */
	public PwUser(String userNum, String password) {
		this.userNum = userNum;
		this.password = password;
	}

	/** full constructor */
	public PwUser(Integer userTypeId, Integer departmentId, String userNum,
			String userName, String password, String remark, Boolean toUse) {
		this.userTypeId = userTypeId;
		this.departmentId = departmentId;
		this.userNum = userNum;
		this.userName = userName;
		this.password = password;
		this.remark = remark;
		this.toUse = toUse;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getToUse() {
		return this.toUse;
	}

	public void setToUse(Boolean toUse) {
		this.toUse = toUse;
	}

}