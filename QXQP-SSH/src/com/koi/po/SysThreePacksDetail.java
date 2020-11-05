package com.koi.po;

/**
 * SysThreePacksDetail entity. @author MyEclipse Persistence Tools
 */

public class SysThreePacksDetail implements java.io.Serializable {

	// Fields

	private Integer threePacksDetailId;
	private Integer receptionId;
	private Integer claimComId;
	private Double claimMoney;

	// Constructors

	/** default constructor */
	public SysThreePacksDetail() {
	}

	/** minimal constructor */
	public SysThreePacksDetail(Integer receptionId) {
		this.receptionId = receptionId;
	}

	/** full constructor */
	public SysThreePacksDetail(Integer receptionId, Integer claimComId,
			Double claimMoney) {
		this.receptionId = receptionId;
		this.claimComId = claimComId;
		this.claimMoney = claimMoney;
	}

	// Property accessors

	public Integer getThreePacksDetailId() {
		return this.threePacksDetailId;
	}

	public void setThreePacksDetailId(Integer threePacksDetailId) {
		this.threePacksDetailId = threePacksDetailId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public Integer getClaimComId() {
		return this.claimComId;
	}

	public void setClaimComId(Integer claimComId) {
		this.claimComId = claimComId;
	}

	public Double getClaimMoney() {
		return this.claimMoney;
	}

	public void setClaimMoney(Double claimMoney) {
		this.claimMoney = claimMoney;
	}

}