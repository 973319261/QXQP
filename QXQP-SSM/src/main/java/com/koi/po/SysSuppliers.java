package com.koi.po;

/**
 * SysSuppliers entity. @author MyEclipse Persistence Tools
 */

public class SysSuppliers implements java.io.Serializable {

	// Fields

	private Integer suppliersId;
	private String suppliersFirm;
	private String suppliersName;
	private String pinYinCode;
	private String mainBusiness;
	private String address;
	private String contacts;
	private String telePhone;
	private String mobilePhone;
	private String facsimile;
	private String mailbox;
	private String url;
	private String openBank;
	private String taxNumber;
	private String inputPerson;
	private String storageAdd;
	private String storageTele;
	private String remark;
	private Boolean toDeactivate;
	private String balanceMode;
	private Double deserveMoney;

	// Constructors

	/** default constructor */
	public SysSuppliers() {
	}

	/** minimal constructor */
	public SysSuppliers(String suppliersFirm, String suppliersName,
			String pinYinCode, String mainBusiness) {
		this.suppliersFirm = suppliersFirm;
		this.suppliersName = suppliersName;
		this.pinYinCode = pinYinCode;
		this.mainBusiness = mainBusiness;
	}

	/** full constructor */
	public SysSuppliers(String suppliersFirm, String suppliersName,
			String pinYinCode, String mainBusiness, String address,
			String contacts, String telePhone, String mobilePhone,
			String facsimile, String mailbox, String url, String openBank,
			String taxNumber, String inputPerson, String storageAdd,
			String storageTele, String remark, Boolean toDeactivate,
			String balanceMode, Double deserveMoney) {
		this.suppliersFirm = suppliersFirm;
		this.suppliersName = suppliersName;
		this.pinYinCode = pinYinCode;
		this.mainBusiness = mainBusiness;
		this.address = address;
		this.contacts = contacts;
		this.telePhone = telePhone;
		this.mobilePhone = mobilePhone;
		this.facsimile = facsimile;
		this.mailbox = mailbox;
		this.url = url;
		this.openBank = openBank;
		this.taxNumber = taxNumber;
		this.inputPerson = inputPerson;
		this.storageAdd = storageAdd;
		this.storageTele = storageTele;
		this.remark = remark;
		this.toDeactivate = toDeactivate;
		this.balanceMode = balanceMode;
		this.deserveMoney = deserveMoney;
	}

	// Property accessors

	public Integer getSuppliersId() {
		return this.suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getSuppliersFirm() {
		return this.suppliersFirm;
	}

	public void setSuppliersFirm(String suppliersFirm) {
		this.suppliersFirm = suppliersFirm;
	}

	public String getSuppliersName() {
		return this.suppliersName;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public String getPinYinCode() {
		return this.pinYinCode;
	}

	public void setPinYinCode(String pinYinCode) {
		this.pinYinCode = pinYinCode;
	}

	public String getMainBusiness() {
		return this.mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
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

	public String getFacsimile() {
		return this.facsimile;
	}

	public void setFacsimile(String facsimile) {
		this.facsimile = facsimile;
	}

	public String getMailbox() {
		return this.mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getTaxNumber() {
		return this.taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getInputPerson() {
		return this.inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

	public String getStorageAdd() {
		return this.storageAdd;
	}

	public void setStorageAdd(String storageAdd) {
		this.storageAdd = storageAdd;
	}

	public String getStorageTele() {
		return this.storageTele;
	}

	public void setStorageTele(String storageTele) {
		this.storageTele = storageTele;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

	public String getBalanceMode() {
		return this.balanceMode;
	}

	public void setBalanceMode(String balanceMode) {
		this.balanceMode = balanceMode;
	}

	public Double getDeserveMoney() {
		return this.deserveMoney;
	}

	public void setDeserveMoney(Double deserveMoney) {
		this.deserveMoney = deserveMoney;
	}

}