package com.gx.pojo;

public class User {
	private Integer UserID;
	private Integer UserTypeID;
	private String UserNum;
	private String UserName;
	private String Password;
	private String Remark;
	private Boolean ToUse;
	private String UserTypeName;
	public String getUserTypeName() {
		return UserTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		UserTypeName = userTypeName;
	}
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public Integer getUserTypeID() {
		return UserTypeID;
	}
	public void setUserTypeID(Integer userTypeID) {
		UserTypeID = userTypeID;
	}
	public String getUserNum() {
		return UserNum;
	}
	public void setUserNum(String userNum) {
		UserNum = userNum;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Boolean getToUse() {
		return ToUse;
	}
	public void setToUse(Boolean toUse) {
		ToUse = toUse;
	}
	
}
