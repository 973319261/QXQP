package com.gx.vo;

import java.util.Date;

public class CollageVo {
	private Integer CollageID;
    private String ForeMan;
    private Integer ReceptionID;
    private Date CollageDate;
    private Double Amount;
    private Boolean ToAudit;
    private Date AuditDate;
    private String Auditor;
    private String Remark;
    private String Operator;
    
    private String CarNum;
    private String Owner;
    private String MaintenanceNum;
    private String CollageState;
    private String StrAudit;
    
	public Integer getCollageID() {
		return CollageID;
	}
	public void setCollageID(Integer collageID) {
		CollageID = collageID;
	}
	public String getForeMan() {
		return ForeMan;
	}
	public void setForeMan(String foreMan) {
		ForeMan = foreMan;
	}
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Date getCollageDate() {
		return CollageDate;
	}
	public void setCollageDate(Date collageDate) {
		CollageDate = collageDate;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Boolean getToAudit() {
		return ToAudit;
	}
	public void setToAudit(Boolean toAudit) {
		ToAudit = toAudit;
	}
	public Date getAuditDate() {
		return AuditDate;
	}
	public void setAuditDate(Date auditDate) {
		AuditDate = auditDate;
	}
	public String getAuditor() {
		return Auditor;
	}
	public void setAuditor(String auditor) {
		Auditor = auditor;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getCarNum() {
		return CarNum;
	}
	public void setCarNum(String carNum) {
		CarNum = carNum;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getMaintenanceNum() {
		return MaintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		MaintenanceNum = maintenanceNum;
	}
	public String getCollageState() {
		return CollageState;
	}
	public void setCollageState(String collageState) {
		CollageState = collageState;
	}
	public String getStrAudit() {
		return StrAudit;
	}
	public void setStrAudit(String strAudit) {
		StrAudit = strAudit;
	}
	
	
}
