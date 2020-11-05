package com.koi.po;

/**
 * SysSubject entity. @author MyEclipse Persistence Tools
 */

public class SysSubject implements java.io.Serializable {

	// Fields

	private Integer subjectId;
	private String subjectName;
	private String subjectNum;

	// Constructors

	/** default constructor */
	public SysSubject() {
	}

	/** full constructor */
	public SysSubject(String subjectName, String subjectNum) {
		this.subjectName = subjectName;
		this.subjectNum = subjectNum;
	}

	// Property accessors

	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectNum() {
		return this.subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

}