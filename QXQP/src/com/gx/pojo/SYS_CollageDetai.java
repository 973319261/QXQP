package com.gx.pojo;

import java.io.Serializable;

public class SYS_CollageDetai implements Serializable{
	  private Integer CollageDetaiID;
      private Integer MaintainabilityID;
      private Integer WarehouseID;
      private Integer CollageID;
      private Double Quantity;
      private Double UnitPrice;
      private Double Amount;
      private String Position;
      private String VehicleType;
      private String FittingsName;
      private String FittingsCode;
      private String FittingsSpec;
      private String SystemUnit;
	public Integer getCollageDetaiID() {
		return CollageDetaiID;
	}
	public void setCollageDetaiID(Integer collageDetaiID) {
		CollageDetaiID = collageDetaiID;
	}
	public Integer getMaintainabilityID() {
		return MaintainabilityID;
	}
	public void setMaintainabilityID(Integer maintainabilityID) {
		MaintainabilityID = maintainabilityID;
	}
	public Integer getWarehouseID() {
		return WarehouseID;
	}
	public void setWarehouseID(Integer warehouseID) {
		WarehouseID = warehouseID;
	}
	public Integer getCollageID() {
		return CollageID;
	}
	public void setCollageID(Integer collageID) {
		CollageID = collageID;
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
	public Double getAmount() {
		return Amount;
	}
	public void setAmount(Double amount) {
		Amount = amount;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public String getFittingsName() {
		return FittingsName;
	}
	public void setFittingsName(String fittingsName) {
		FittingsName = fittingsName;
	}
	public String getFittingsCode() {
		return FittingsCode;
	}
	public void setFittingsCode(String fittingsCode) {
		FittingsCode = fittingsCode;
	}
	public String getFittingsSpec() {
		return FittingsSpec;
	}
	public void setFittingsSpec(String fittingsSpec) {
		FittingsSpec = fittingsSpec;
	}
	public String getSystemUnit() {
		return SystemUnit;
	}
	public void setSystemUnit(String systemUnit) {
		SystemUnit = systemUnit;
	}
      
}
