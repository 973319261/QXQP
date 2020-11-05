package com.koi.po;

/**
 * SysSalesCustomer entity. @author MyEclipse Persistence Tools
 */

public class SysSalesCustomer implements java.io.Serializable {

	// Fields

	private Integer salesCustomerId;
	private Integer customerLevelId;
	private Integer customerTypeId;
	private Integer paymentId;
	private Integer userId;
	private Integer regionId;
	private Integer customerSouId;
	private String customerCode;
	private String customerName;
	private String address;
	private String zipCode;
	private String fullName;
	private String pinYinCode;
	private String idNumber;
	private String contacts;
	private String facsimile;
	private String telePhone;
	private String mobilePhone;
	private String emai;
	private String remark;
	private String inputPerson;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysSalesCustomer() {
	}

	/** minimal constructor */
	public SysSalesCustomer(Integer customerLevelId, Integer customerTypeId,
			Integer paymentId, Integer userId, Integer regionId,
			Integer customerSouId, String customerCode, String customerName) {
		this.customerLevelId = customerLevelId;
		this.customerTypeId = customerTypeId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.regionId = regionId;
		this.customerSouId = customerSouId;
		this.customerCode = customerCode;
		this.customerName = customerName;
	}

	/** full constructor */
	public SysSalesCustomer(Integer customerLevelId, Integer customerTypeId,
			Integer paymentId, Integer userId, Integer regionId,
			Integer customerSouId, String customerCode, String customerName,
			String address, String zipCode, String fullName, String pinYinCode,
			String idNumber, String contacts, String facsimile,
			String telePhone, String mobilePhone, String emai, String remark,
			String inputPerson, Boolean toDeactivate) {
		this.customerLevelId = customerLevelId;
		this.customerTypeId = customerTypeId;
		this.paymentId = paymentId;
		this.userId = userId;
		this.regionId = regionId;
		this.customerSouId = customerSouId;
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.address = address;
		this.zipCode = zipCode;
		this.fullName = fullName;
		this.pinYinCode = pinYinCode;
		this.idNumber = idNumber;
		this.contacts = contacts;
		this.facsimile = facsimile;
		this.telePhone = telePhone;
		this.mobilePhone = mobilePhone;
		this.emai = emai;
		this.remark = remark;
		this.inputPerson = inputPerson;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getSalesCustomerId() {
		return this.salesCustomerId;
	}

	public void setSalesCustomerId(Integer salesCustomerId) {
		this.salesCustomerId = salesCustomerId;
	}

	public Integer getCustomerLevelId() {
		return this.customerLevelId;
	}

	public void setCustomerLevelId(Integer customerLevelId) {
		this.customerLevelId = customerLevelId;
	}

	public Integer getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public Integer getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getCustomerSouId() {
		return this.customerSouId;
	}

	public void setCustomerSouId(Integer customerSouId) {
		this.customerSouId = customerSouId;
	}

	public String getCustomerCode() {
		return this.customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPinYinCode() {
		return this.pinYinCode;
	}

	public void setPinYinCode(String pinYinCode) {
		this.pinYinCode = pinYinCode;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getFacsimile() {
		return this.facsimile;
	}

	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}

	public String getTelePhone() {
		return this.telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmai() {
		return this.emai;
	}

	public void setEmai(String emai) {
		this.emai = emai;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInputPerson() {
		return this.inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}