package com.koi.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.koi.util.Util;

public class CollageVo {
	private Integer collageId;
	private String foreMan;
	private Integer receptionId;
	private String collageDate;
	private Double amount;
	private Boolean toAudit;
	private Timestamp auditDate;
	private String auditor;
	private String remark;
	private String operator;

	private String carNum;
	private String owner;
	private String maintenanceNum;
	private String collageState;
	private String StrAudit;
	public CollageVo() {
	}

	public CollageVo(Integer collageId, String foreMan, Integer receptionId,
			String collageDate, Double amount, Boolean toAudit, Timestamp auditDate,
			String auditor, String remark, String operator, String carNum,
			String owner, String maintenanceNum, String collageState) {
		super();
		this.collageId = collageId;
		this.foreMan = foreMan;
		this.receptionId = receptionId;
		this.collageDate = collageDate;
		this.amount = amount;
		this.toAudit = toAudit;
		this.auditDate = auditDate;
		this.auditor = auditor;
		this.remark = remark;
		this.operator = operator;
		this.carNum = carNum;
		this.owner = owner;
		this.maintenanceNum = maintenanceNum;
		this.collageState = collageState;
	}

	public Integer getCollageId() {
		return collageId;
	}

	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
	}

	public String getForeMan() {
		return foreMan;
	}

	public void setForeMan(String foreMan) {
		this.foreMan = foreMan;
	}

	public Integer getReceptionId() {
		return receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public String getCollageDate() {
		return collageDate;
	}

	public void setCollageDate(String collageDate) {
		this.collageDate = collageDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getToAudit() {
		return toAudit;
	}

	public void setToAudit(Boolean toAudit) {
		this.toAudit = toAudit;
	}

	public Date getAuditDate() {
		return Util.strToUtilDate(this.auditDate,"yyyy-MM-dd HH:mm:ss");
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMaintenanceNum() {
		return maintenanceNum;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public String getCollageState() {
		return collageState;
	}

	public void setCollageState(String collageState) {
		this.collageState = collageState;
	}

	public String getStrAudit() {
		return StrAudit;
	}

	public void setStrAudit(String strAudit) {
		StrAudit = strAudit;
	}
	
}
