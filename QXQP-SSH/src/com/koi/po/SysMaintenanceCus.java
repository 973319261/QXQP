package com.koi.po;

/**
 * SysMaintenanceCus entity. @author MyEclipse Persistence Tools
 */

public class SysMaintenanceCus implements java.io.Serializable {

	// Fields

	private Integer maintenanceCusId;
	private String vehicleType;
	private Integer insuranceComId;
	private Integer insuranceSpeId;
	private Integer departmentId;
	private Integer carderId;
	private Integer customerTypeId;
	private Integer customerLevelId;
	private Integer regionId;
	private Integer customerSouId;
	private String owner;
	private String birthday;
	private String mobilePhone;
	private String idNumber;
	private String licenseCode;
	private String engineNum;
	private String frameNum;
	private String repairMan;
	private String repairTele;
	private String initialStartDate;
	private String initialEndDate;
	private String address;
	private String customerNum;
	private String driveDate;
	private String drivingDate;
	private String inputPerson;

	// Constructors

	/** default constructor */
	public SysMaintenanceCus() {
	}


	public SysMaintenanceCus(Integer maintenanceCusId, String vehicleType,
			Integer insuranceComId, Integer insuranceSpeId,
			Integer departmentId, Integer carderId, Integer customerTypeId,
			Integer customerLevelId, Integer regionId, Integer customerSouId,
			String owner, String birthday, String mobilePhone, String idNumber,
			String licenseCode, String engineNum, String frameNum,
			String repairMan, String repairTele, String initialStartDate,
			String initialEndDate, String address, String customerNum,
			String driveDate, String drivingDate, String inputPerson) {
		super();
		this.maintenanceCusId = maintenanceCusId;
		this.vehicleType = vehicleType;
		this.insuranceComId = insuranceComId;
		this.insuranceSpeId = insuranceSpeId;
		this.departmentId = departmentId;
		this.carderId = carderId;
		this.customerTypeId = customerTypeId;
		this.customerLevelId = customerLevelId;
		this.regionId = regionId;
		this.customerSouId = customerSouId;
		this.owner = owner;
		this.birthday = birthday;
		this.mobilePhone = mobilePhone;
		this.idNumber = idNumber;
		this.licenseCode = licenseCode;
		this.engineNum = engineNum;
		this.frameNum = frameNum;
		this.repairMan = repairMan;
		this.repairTele = repairTele;
		this.initialStartDate = initialStartDate;
		this.initialEndDate = initialEndDate;
		this.address = address;
		this.customerNum = customerNum;
		this.driveDate = driveDate;
		this.drivingDate = drivingDate;
		this.inputPerson = inputPerson;
	}


	// Property accessors

	public Integer getMaintenanceCusId() {
		return this.maintenanceCusId;
	}

	public void setMaintenanceCusId(Integer maintenanceCusId) {
		this.maintenanceCusId = maintenanceCusId;
	}

	public String getVehicleType() {
		return this.vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getInsuranceComId() {
		return this.insuranceComId;
	}

	public void setInsuranceComId(Integer insuranceComId) {
		this.insuranceComId = insuranceComId;
	}

	public Integer getInsuranceSpeId() {
		return this.insuranceSpeId;
	}

	public void setInsuranceSpeId(Integer insuranceSpeId) {
		this.insuranceSpeId = insuranceSpeId;
	}

	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getCarderId() {
		return this.carderId;
	}

	public void setCarderId(Integer carderId) {
		this.carderId = carderId;
	}

	public Integer getCustomerTypeId() {
		return this.customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	public Integer getCustomerLevelId() {
		return this.customerLevelId;
	}

	public void setCustomerLevelId(Integer customerLevelId) {
		this.customerLevelId = customerLevelId;
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

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getLicenseCode() {
		return this.licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public String getEngineNum() {
		return this.engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getFrameNum() {
		return this.frameNum;
	}

	public void setFrameNum(String frameNum) {
		this.frameNum = frameNum;
	}

	public String getRepairMan() {
		return this.repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	public String getRepairTele() {
		return this.repairTele;
	}

	public void setRepairTele(String repairTele) {
		this.repairTele = repairTele;
	}

	public String getInitialStartDate() {
		return this.initialStartDate;
	}

	public void setInitialStartDate(String initialStartDate) {
		this.initialStartDate = initialStartDate;
	}

	public String getInitialEndDate() {
		return this.initialEndDate;
	}

	public void setInitialEndDate(String initialEndDate) {
		this.initialEndDate = initialEndDate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getDriveDate() {
		return this.driveDate;
	}

	public void setDriveDate(String driveDate) {
		this.driveDate = driveDate;
	}

	public String getDrivingDate() {
		return this.drivingDate;
	}

	public void setDrivingDate(String drivingDate) {
		this.drivingDate = drivingDate;
	}

	public String getInputPerson() {
		return this.inputPerson;
	}

	public void setInputPerson(String inputPerson) {
		this.inputPerson = inputPerson;
	}

}