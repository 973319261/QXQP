package com.koi.qxqp.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

@SmartTable(name = "产品材料",count = true)
public class PreProductDetailVo implements java.io.Serializable {
	private Integer preProductDetailId;
	private Integer predateId;
	private Integer maintainabilityId;
	@SmartColumn(id =4,name = "数量",autoCount=true)
	private Double quantity;
	@SmartColumn(id =6,name = "单价",autoCount = true)
	private Double unitPrice;
	@SmartColumn(id =7,name = "折扣(%)")
	private Double discount;
	@SmartColumn(id =8,name = "金额",autoCount=true)
	private Double amount;
	@SmartColumn(id =10,name = "备注")
	private String remark;
	@SmartColumn(id =1,name = "配件编号")
	private String fittingsCode;
	@SmartColumn(id =2,name = "配件名称")
	private String fittingsName;
	@SmartColumn(id =5,name = "单位")
	private String systemUnit;
	@SmartColumn(id =3,name = "车型")
	private String vehicleType;
	@SmartColumn(id =9,name = "维修性质")
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
