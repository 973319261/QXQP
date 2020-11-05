package com.koi.po;

/**
 * PwBalance entity. @author MyEclipse Persistence Tools
 */

public class PwBalance implements java.io.Serializable {

	// Fields

	private Integer balanceId;
	private Integer paymentId;
	private Integer receptionId;
	private Integer documentsTypeId;
	private Double shouldAmount;
	private Double optimalAmount;
	private Double collectionAmount;
	private String remark;
	private Integer balanceStateId;
	private String businessNum;

	// Constructors

	/** default constructor */
	public PwBalance() {
	}

	/** minimal constructor */
	public PwBalance(Integer paymentId, Integer receptionId,
			Integer documentsTypeId, Double shouldAmount, Double optimalAmount,
			Double collectionAmount, Integer balanceStateId, String businessNum) {
		this.paymentId = paymentId;
		this.receptionId = receptionId;
		this.documentsTypeId = documentsTypeId;
		this.shouldAmount = shouldAmount;
		this.optimalAmount = optimalAmount;
		this.collectionAmount = collectionAmount;
		this.balanceStateId = balanceStateId;
		this.businessNum = businessNum;
	}

	/** full constructor */
	public PwBalance(Integer paymentId, Integer receptionId,
			Integer documentsTypeId, Double shouldAmount, Double optimalAmount,
			Double collectionAmount, String remark, Integer balanceStateId,
			String businessNum) {
		this.paymentId = paymentId;
		this.receptionId = receptionId;
		this.documentsTypeId = documentsTypeId;
		this.shouldAmount = shouldAmount;
		this.optimalAmount = optimalAmount;
		this.collectionAmount = collectionAmount;
		this.remark = remark;
		this.balanceStateId = balanceStateId;
		this.businessNum = businessNum;
	}

	// Property accessors

	public Integer getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getReceptionId() {
		return this.receptionId;
	}

	public void setReceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}

	public Integer getDocumentsTypeId() {
		return this.documentsTypeId;
	}

	public void setDocumentsTypeId(Integer documentsTypeId) {
		this.documentsTypeId = documentsTypeId;
	}

	public Double getShouldAmount() {
		return this.shouldAmount;
	}

	public void setShouldAmount(Double shouldAmount) {
		this.shouldAmount = shouldAmount;
	}

	public Double getOptimalAmount() {
		return this.optimalAmount;
	}

	public void setOptimalAmount(Double optimalAmount) {
		this.optimalAmount = optimalAmount;
	}

	public Double getCollectionAmount() {
		return this.collectionAmount;
	}

	public void setCollectionAmount(Double collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBalanceStateId() {
		return this.balanceStateId;
	}

	public void setBalanceStateId(Integer balanceStateId) {
		this.balanceStateId = balanceStateId;
	}

	public String getBusinessNum() {
		return this.businessNum;
	}

	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}

}