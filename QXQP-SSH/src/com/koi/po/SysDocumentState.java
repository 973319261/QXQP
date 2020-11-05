package com.koi.po;

/**
 * SysDocumentState entity. @author MyEclipse Persistence Tools
 */

public class SysDocumentState implements java.io.Serializable {

	// Fields

	private Integer documentStateId;
	private String documentState;

	// Constructors

	/** default constructor */
	public SysDocumentState() {
	}

	/** full constructor */
	public SysDocumentState(String documentState) {
		this.documentState = documentState;
	}

	// Property accessors

	public Integer getDocumentStateId() {
		return this.documentStateId;
	}

	public void setDocumentStateId(Integer documentStateId) {
		this.documentStateId = documentStateId;
	}

	public String getDocumentState() {
		return this.documentState;
	}

	public void setDocumentState(String documentState) {
		this.documentState = documentState;
	}

}