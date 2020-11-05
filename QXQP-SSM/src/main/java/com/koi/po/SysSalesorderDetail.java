package com.koi.po;

/**
 * SysSalesorderDetail entity. @author MyEclipse Persistence Tools
 */

public class SysSalesorderDetail implements java.io.Serializable {

	// Fields

	private Integer salesorderDetailId;
	private Integer salesordeId;
	private Integer warehouseId;
	private Integer fittingsInfoId;
	private Double amount;
	private Double discount;
	private Double money;
	private String atretail;
	private Double unitcost;
	private String remark;
	private String fittingsType;

	// Constructors

	/** default constructor */
	public SysSalesorderDetail() {
	}

	/** minimal constructor */
	public SysSalesorderDetail(Integer salesordeId, Integer warehouseId,
			Integer fittingsInfoId) {
		this.salesordeId = salesordeId;
		this.warehouseId = warehouseId;
		this.fittingsInfoId = fittingsInfoId;
	}

	/** full constructor */
	public SysSalesorderDetail(Integer salesordeId, Integer warehouseId,
			Integer fittingsInfoId, Double amount, Double discount,
			Double money, String atretail, Double unitcost, String remark,
			String fittingsType) {
		this.salesordeId = salesordeId;
		this.warehouseId = warehouseId;
		this.fittingsInfoId = fittingsInfoId;
		this.amount = amount;
		this.discount = discount;
		this.money = money;
		this.atretail = atretail;
		this.unitcost = unitcost;
		this.remark = remark;
		this.fittingsType = fittingsType;
	}

	// Property accessors

	public Integer getSalesorderDetailId() {
		return this.salesorderDetailId;
	}

	public void setSalesorderDetailId(Integer salesorderDetailId) {
		this.salesorderDetailId = salesorderDetailId;
	}

	public Integer getSalesordeId() {
		return this.salesordeId;
	}

	public void setSalesordeId(Integer salesordeId) {
		this.salesordeId = salesordeId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getFittingsInfoId() {
		return this.fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getAtretail() {
		return this.atretail;
	}

	public void setAtretail(String atretail) {
		this.atretail = atretail;
	}

	public Double getUnitcost() {
		return this.unitcost;
	}

	public void setUnitcost(Double unitcost) {
		this.unitcost = unitcost;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFittingsType() {
		return this.fittingsType;
	}

	public void setFittingsType(String fittingsType) {
		this.fittingsType = fittingsType;
	}

}