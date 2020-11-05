package com.gx.vo;

public class RestockDetail {
	  private Integer CollageDetaiID;
      private Double Quantity;
      private String FittingsCode;
      private Integer WarehouseID;
	public Integer getCollageDetaiID() {
		return CollageDetaiID;
	}
	public void setCollageDetaiID(Integer collageDetaiID) {
		CollageDetaiID = collageDetaiID;
	}
	public Double getQuantity() {
		return Quantity;
	}
	public void setQuantity(Double quantity) {
		Quantity = quantity;
	}
	public String getFittingsCode() {
		return FittingsCode;
	}
	public void setFittingsCode(String fittingsCode) {
		FittingsCode = fittingsCode;
	}
	public Integer getWarehouseID() {
		return WarehouseID;
	}
	public void setWarehouseID(Integer warehouseID) {
		WarehouseID = warehouseID;
	}
      
}
