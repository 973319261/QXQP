package com.koi.vo;

public class ProductVo {
	private Integer recProductDetailId;
	private Integer maintainabilityId;
	private Integer receptionId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String fittingsCode;
	private String fittingsName;
	private String vehicleType;
	private String remark;
	private String fittingsSpec;
	private String systemUnit;
	
	private String maintainabilityName;
	public ProductVo() {
	}
	public ProductVo(Integer recProductDetailId, Integer maintainabilityId,
			Integer receptionId, Double quantity, Double unitPrice,
			Double discount, Double amount, String fittingsCode,
			String fittingsName, String vehicleType, String remark,
			String fittingsSpec, String systemUnit, String maintainabilityName) {
		super();
		this.recProductDetailId = recProductDetailId;
		this.maintainabilityId = maintainabilityId;
		this.receptionId = receptionId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.amount = amount;
		this.fittingsCode = fittingsCode;
		this.fittingsName = fittingsName;
		this.vehicleType = vehicleType;
		this.remark = remark;
		this.fittingsSpec = fittingsSpec;
		this.systemUnit = systemUnit;
		this.maintainabilityName = maintainabilityName;
	}
	public Integer getrecProductDetailId() {
		return recProductDetailId;
	}
	public void setrecProductDetailId(Integer recProductDetailId) {
		this.recProductDetailId = recProductDetailId;
	}
	public Integer getmaintainabilityId() {
		return maintainabilityId;
	}
	public void setmaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}
	public Integer getreceptionId() {
		return receptionId;
	}
	public void setreceptionId(Integer receptionId) {
		this.receptionId = receptionId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getFittingsCode() {
		return fittingsCode;
	}
	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
	}
	public String getFittingsName() {
		return fittingsName;
	}
	public void setFittingsName(String fittingsName) {
		this.fittingsName = fittingsName;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFittingsSpec() {
		return fittingsSpec;
	}
	public void setFittingsSpec(String fittingsSpec) {
		this.fittingsSpec = fittingsSpec;
	}
	public String getSystemUnit() {
		return systemUnit;
	}
	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}
	public String getMaintainabilityName() {
		return maintainabilityName;
	}
	public void setMaintainabilityName(String maintainabilityName) {
		this.maintainabilityName = maintainabilityName;
	}

}
