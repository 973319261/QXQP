package com.koi.po;

/**
 * SysDocumentsType entity. @author MyEclipse Persistence Tools
 */

public class SysDocumentsType implements java.io.Serializable {

	// Fields

	private Integer documentsTypeId;
	private String documentsType;

	// Constructors

	/** default constructor */
	public SysDocumentsType() {
	}

	/** full constructor */
	public SysDocumentsType(String documentsType) {
		this.documentsType = documentsType;
	}

	// Property accessors

	public Integer getDocumentsTypeId() {
		return this.documentsTypeId;
	}

	public void setDocumentsTypeId(Integer documentsTypeId) {
		this.documentsTypeId = documentsTypeId;
	}

	public String getDocumentsType() {
		return this.documentsType;
	}

	public void setDocumentsType(String documentsType) {
		this.documentsType = documentsType;
	}

}