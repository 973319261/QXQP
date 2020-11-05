package com.gx.vo;

import java.util.Date;

import com.gx.pojo.PW_Reception;

public class ReceptionVo extends PW_Reception{
	private Integer ReceptionID;
    private Integer DocumentStateID;
    private Integer BalanceStateID;
    private String VehicleType;
    private String Carder;
    private Integer RepairID;
    private Integer CustomerSouID;
    private String CarNum;
    private String Mileage;
    private String Owner;
    private String Address;
    private String OilQuantity;
    private String OwnerTele;
    private String EngineNum;
    private String Repairman;
    private String SelfCoding;
    private String FrameNum;
    private String RepairmanTele;
    private Date FactoryDate;
    private Date BalanceDate;
    private Double Amount;
    private Double AmountPaid;
    private Boolean ToAudit;
    private Date CompletionDate;
    private String Describe;
    private String MaintenanceNum;
    private String CollageState;
    private Boolean ToSendWork;
    private Date OpenDate;
    private Double MaintenAmount;
    private Boolean ToCompletion;
    private String CustomerNum;
    
    private String RepairName;
	private String CustomerSou;
	private String DocumentState;
	private String BalanceState;
	
	private Double InsuranceMoney;
	private Integer InsuranceDetailID;
	private String ReportNum;
	private String PolicyNum;
	private Double PolicyMoney;
	private Integer InsuranceComID;
	private Integer InsuranceID;
	private String ClaimsStaff;
	private Boolean ToTicket;
	private String InsuranceNum;
	private String Remark;
	
	private String ThreePacksDetailID;
	private String ThreePacksID;
	private String Operator;
	private Integer ClaimComID;
	private Double ClaimMoney;
	public Integer getReceptionID() {
		return ReceptionID;
	}
	public void setReceptionID(Integer receptionID) {
		ReceptionID = receptionID;
	}
	public Integer getDocumentStateID() {
		return DocumentStateID;
	}
	public void setDocumentStateID(Integer documentStateID) {
		DocumentStateID = documentStateID;
	}
	public Integer getBalanceStateID() {
		return BalanceStateID;
	}
	public void setBalanceStateID(Integer balanceStateID) {
		BalanceStateID = balanceStateID;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public String getCarder() {
		return Carder;
	}
	public void setCarder(String carder) {
		Carder = carder;
	}
	public Integer getRepairID() {
		return RepairID;
	}
	public void setRepairID(Integer repairID) {
		RepairID = repairID;
	}
	public Integer getCustomerSouID() {
		return CustomerSouID;
	}
	public void setCustomerSouID(Integer customerSouID) {
		CustomerSouID = customerSouID;
	}
	public String getCarNum() {
		return CarNum;
	}
	public void setCarNum(String carNum) {
		CarNum = carNum;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getOilQuantity() {
		return OilQuantity;
	}
	public void setOilQuantity(String oilQuantity) {
		OilQuantity = oilQuantity;
	}
	public String getOwnerTele() {
		return OwnerTele;
	}
	public void setOwnerTele(String ownerTele) {
		OwnerTele = ownerTele;
	}
	public String getEngineNum() {
		return EngineNum;
	}
	public void setEngineNum(String engineNum) {
		EngineNum = engineNum;
	}
	public String getRepairman() {
		return Repairman;
	}
	public void setRepairman(String repairman) {
		Repairman = repairman;
	}
	public String getSelfCoding() {
		return SelfCoding;
	}
	public void setSelfCoding(String selfCoding) {
		SelfCoding = selfCoding;
	}
	public String getFrameNum() {
		return FrameNum;
	}
	public void setFrameNum(String frameNum) {
		FrameNum = frameNum;
	}
	public String getRepairmanTele() {
		return RepairmanTele;
	}
	public void setRepairmanTele(String repairmanTele) {
		RepairmanTele = repairmanTele;
	}
	public Date getFactoryDate() {
		return FactoryDate;
	}
	public void setFactoryDate(Date factoryDate) {
		FactoryDate = factoryDate;
	}
	public Date getBalanceDate() {
		return BalanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		BalanceDate = balanceDate;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public Double getAmountPaid() {
		return AmountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		AmountPaid = amountPaid;
	}
	public Boolean getToAudit() {
		return ToAudit;
	}
	public void setToAudit(Boolean toAudit) {
		ToAudit = toAudit;
	}
	public Date getCompletionDate() {
		return CompletionDate;
	}
	public void setCompletionDate(Date completionDate) {
		CompletionDate = completionDate;
	}
	public String getDescribe() {
		return Describe;
	}
	public void setDescribe(String describe) {
		Describe = describe;
	}
	public String getMaintenanceNum() {
		return MaintenanceNum;
	}
	public void setMaintenanceNum(String maintenanceNum) {
		MaintenanceNum = maintenanceNum;
	}
	public String getCollageState() {
		return CollageState;
	}
	public void setCollageState(String collageState) {
		CollageState = collageState;
	}
	public Boolean getToSendWork() {
		return ToSendWork;
	}
	public void setToSendWork(Boolean toSendWork) {
		ToSendWork = toSendWork;
	}
	public Date getOpenDate() {
		return OpenDate;
	}
	public void setOpenDate(Date openDate) {
		OpenDate = openDate;
	}
	public Double getMaintenAmount() {
		return MaintenAmount;
	}
	public void setMaintenAmount(Double maintenAmount) {
		MaintenAmount = maintenAmount;
	}
	public Boolean getToCompletion() {
		return ToCompletion;
	}
	public void setToCompletion(Boolean toCompletion) {
		ToCompletion = toCompletion;
	}
	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		CustomerNum = customerNum;
	}
	public String getRepairName() {
		return RepairName;
	}
	public void setRepairName(String repairName) {
		RepairName = repairName;
	}
	public String getCustomerSou() {
		return CustomerSou;
	}
	public void setCustomerSou(String customerSou) {
		CustomerSou = customerSou;
	}
	public String getDocumentState() {
		return DocumentState;
	}
	public void setDocumentState(String documentState) {
		DocumentState = documentState;
	}
	public String getBalanceState() {
		return BalanceState;
	}
	public void setBalanceState(String balanceState) {
		BalanceState = balanceState;
	}
	public Double getInsuranceMoney() {
		return InsuranceMoney;
	}
	public void setInsuranceMoney(Double insuranceMoney) {
		InsuranceMoney = insuranceMoney;
	}
	public Integer getInsuranceDetailID() {
		return InsuranceDetailID;
	}
	public void setInsuranceDetailID(Integer insuranceDetailID) {
		InsuranceDetailID = insuranceDetailID;
	}
	public String getReportNum() {
		return ReportNum;
	}
	public void setReportNum(String reportNum) {
		ReportNum = reportNum;
	}
	public String getPolicyNum() {
		return PolicyNum;
	}
	public void setPolicyNum(String policyNum) {
		PolicyNum = policyNum;
	}
	public Double getPolicyMoney() {
		return PolicyMoney;
	}
	public void setPolicyMoney(Double policyMoney) {
		PolicyMoney = policyMoney;
	}
	public Integer getInsuranceComID() {
		return InsuranceComID;
	}
	public void setInsuranceComID(Integer insuranceComID) {
		InsuranceComID = insuranceComID;
	}
	public Integer getInsuranceID() {
		return InsuranceID;
	}
	public void setInsuranceID(Integer insuranceID) {
		InsuranceID = insuranceID;
	}
	public String getClaimsStaff() {
		return ClaimsStaff;
	}
	public void setClaimsStaff(String claimsStaff) {
		ClaimsStaff = claimsStaff;
	}
	public Boolean getToTicket() {
		return ToTicket;
	}
	public void setToTicket(Boolean toTicket) {
		ToTicket = toTicket;
	}
	public String getInsuranceNum() {
		return InsuranceNum;
	}
	public void setInsuranceNum(String insuranceNum) {
		InsuranceNum = insuranceNum;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getThreePacksDetailID() {
		return ThreePacksDetailID;
	}
	public void setThreePacksDetailID(String threePacksDetailID) {
		ThreePacksDetailID = threePacksDetailID;
	}
	public String getThreePacksID() {
		return ThreePacksID;
	}
	public void setThreePacksID(String threePacksID) {
		ThreePacksID = threePacksID;
	}
	public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public Integer getClaimComID() {
		return ClaimComID;
	}
	public void setClaimComID(Integer claimComID) {
		ClaimComID = claimComID;
	}
	public Double getClaimMoney() {
		return ClaimMoney;
	}
	public void setClaimMoney(Double claimMoney) {
		ClaimMoney = claimMoney;
	}
	
}
