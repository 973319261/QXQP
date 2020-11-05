package com.koi.po;

/**
 * SysRestockDetail entity. @author MyEclipse Persistence Tools
 */

public class SysRestockDetail implements java.io.Serializable {

	// Fields

	private Integer restockDetaiId;
	private Integer collageDetaiId;
	private Integer restockId;

	// Constructors

	/** default constructor */
	public SysRestockDetail() {
	}

	/** full constructor */
	public SysRestockDetail(Integer collageDetaiId, Integer restockId) {
		this.collageDetaiId = collageDetaiId;
		this.restockId = restockId;
	}

	// Property accessors

	public Integer getRestockDetaiId() {
		return this.restockDetaiId;
	}

	public void setRestockDetaiId(Integer restockDetaiId) {
		this.restockDetaiId = restockDetaiId;
	}

	public Integer getCollageDetaiId() {
		return this.collageDetaiId;
	}

	public void setCollageDetaiId(Integer collageDetaiId) {
		this.collageDetaiId = collageDetaiId;
	}

	public Integer getRestockId() {
		return this.restockId;
	}

	public void setRestockId(Integer restockId) {
		this.restockId = restockId;
	}

}