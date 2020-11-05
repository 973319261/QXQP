package com.koi.vo;

public class RestockDetailVo {
	private Integer collageDetaiID;
	private Double quantity;
	private String fittingsCode;
	private Integer warehouseID;

	public RestockDetailVo() {
	}

	public RestockDetailVo(Integer collageDetaiID, Double quantity,
			String fittingsCode, Integer warehouseID) {
		super();
		this.collageDetaiID = collageDetaiID;
		this.quantity = quantity;
		this.fittingsCode = fittingsCode;
		this.warehouseID = warehouseID;
	}

	public Integer getCollageDetaiID() {
		return collageDetaiID;
	}

	public void setCollageDetaiID(Integer collageDetaiID) {
		this.collageDetaiID = collageDetaiID;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getFittingsCode() {
		return fittingsCode;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
	}

	public Integer getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(Integer warehouseID) {
		this.warehouseID = warehouseID;
	}

}
