package com.koi.vo;

public class PreProductDetailVo implements java.io.Serializable {
	private Integer preProductDetailId;
	private Integer predateId;
	private Integer maintainabilityId;
	private Double quantity;
	private Double unitPrice;
	private Double discount;
	private Double amount;
	private String remark;
	private String fittingsCode;
	private String fittingsName;
	private String systemUnit;
	private String vehicleType;
	
	private String maintainabilityName;

	public Integer getPreProductDetailId() {
		return preProductDetailId;
	}

	public void setPreProductDetailId(Integer preProductDetailId) {
		this.preProductDetailId = preProductDetailId;
	}

	public Integer getPredateId() {
		return predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getMaintainabilityId() {
		return maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getSystemUnit() {
		return systemUnit;
	}

	public void setSystemUnit(String systemUnit) {
		this.systemUnit = systemUnit;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getMaintainabilityName() {
		return maintainabilityName;
	}

	public void setMaintainabilityName(String maintainabilityName) {
		this.maintainabilityName = maintainabilityName;
	}
	
}
