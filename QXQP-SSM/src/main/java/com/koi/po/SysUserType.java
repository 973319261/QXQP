package com.koi.po;

/**
 * SysUserType entity. @author MyEclipse Persistence Tools
 */

public class SysUserType implements java.io.Serializable {

	// Fields

	private Integer userTypeId;
	private String userTypeName;

	// Constructors

	/** default constructor */
	public SysUserType() {
	}

	/** full constructor */
	public SysUserType(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	// Property accessors

	public Integer getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return this.userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

}