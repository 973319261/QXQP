package com.gx.pojo;

import java.io.Serializable;

public class SYS_PreProductDetail implements Serializable{
	private Integer PreProductDetailID;
	private Integer PredateID;
	private Integer MaintainabilityID;
	private Double Quantity;
	private Double UnitPrice;
	private Double Discount;
	private Double Amount;
	private String Remark;
	private String FittingsCode;
	private String FittingsName;
	private String SystemUnit;
	private String VehicleType;
	public Integer getPreProductDetailID() {
		return PreProductDetailID;
	}
	public void setPreProductDetailID(Integer preProductDetailID) {
		PreProductDetailID = preProductDetailID;
	}
	public Integer getPredateID() {
		return PredateID;
	}
	public void setPredateID(Integer predateID) {
		PredateID = predateID;
	}
	public Integer getMaintainabilityID() {
		return MaintainabilityID;
	}
	public void setMaintainabilityID(Integer maintainabilityID) {
		MaintainabilityID = maintainabilityID;
	}
	public Double getQuantity() {
		return Quantity;
	}
	public void setQuantity(Double quantity) {
		Quantity = quantity;
	}
	public Double getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		UnitPrice = unitPrice;
	}
	public Double getDiscount() {
		return Discount;
	}
	public void setDiscount(Double discount) {
		Discount = discount;
	}
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getFittingsCode() {
		return FittingsCode;
	}
	public void setFittingsCode(String fittingsCode) {
		FittingsCode = fittingsCode;
	}
	public String getFittingsName() {
		return FittingsName;
	}
	public void setFittingsName(String fittingsName) {
		FittingsName = fittingsName;
	}
	public String getSystemUnit() {
		return SystemUnit;
	}
	public void setSystemUnit(String systemUnit) {
		SystemUnit = systemUnit;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	
}
