package com.gx.pojo;

public class PW_Balance {
	private Integer BalanceID;
	private Integer PaymentID;
	private Integer ReceptionID;
	private Integer DocumentsTypeID;
	private Double ShouldAmount;
	private Double OptimalAmount;
	private Double CollectionAmount;
	private String Remark;
	private Integer BalanceStateID;
	private String BusinessNum;
	public Integer getBalanceID() {
		return BalanceID;
	}
	public void setBalanceID(Integer balanceID) {
		BalanceID = balanceID;
	}
	public Integer getPaymentID() {
		return PaymentID;
	}
	public void setPaymentID(Integer paymentID) {
		PaymentID = paymentID;
	}
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Integer getDocumentsTypeID() {
		return DocumentsTypeID;
	}
	public void setDocumentsTypeID(Integer documentsTypeID) {
		DocumentsTypeID = documentsTypeID;
	}
	public Double getShouldAmount() {
		return ShouldAmount;
	}
	public void setShouldAmount(Double shouldAmount) {
		ShouldAmount = shouldAmount;
	}
	public Double getOptimalAmount() {
		return OptimalAmount;
	}
	public void setOptimalAmount(Double optimalAmount) {
		OptimalAmount = optimalAmount;
	}
	public Double getCollectionAmount() {
		return CollectionAmount;
	}
	public void setCollectionAmount(Double collectionAmount) {
		CollectionAmount = collectionAmount;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Integer getBalanceStateID() {
		return BalanceStateID;
	}
	public void setBalanceStateID(Integer balanceStateID) {
		BalanceStateID = balanceStateID;
	}
	public String getBusinessNum() {
		return BusinessNum;
	}
	public void setBusinessNum(String businessNum) {
		BusinessNum = businessNum;
	}
	
}
