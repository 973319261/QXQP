package com.koi.po;

/**
 * SysOutboundDetails entity. @author MyEclipse Persistence Tools
 */

public class SysOutboundDetails implements java.io.Serializable {

	// Fields

	private Integer outboundDetailsId;
	private Integer outboundId;
	private Integer fittingsInfoId;
	private Integer warehouseId;
	private Double quantity;
	private Double unitcosts;
	private Double discount;
	private Double figure;
	private String comments;

	// Constructors

	/** default constructor */
	public SysOutboundDetails() {
	}

	/** minimal constructor */
	public SysOutboundDetails(Integer outboundId) {
		this.outboundId = outboundId;
	}

	/** full constructor */
	public SysOutboundDetails(Integer outboundId, Integer fittingsInfoId,
			Integer warehouseId, Double quantity, Double unitcosts,
			Double discount, Double figure, String comments) {
		this.outboundId = outboundId;
		this.fittingsInfoId = fittingsInfoId;
		this.warehouseId = warehouseId;
		this.quantity = quantity;
		this.unitcosts = unitcosts;
		this.discount = discount;
		this.figure = figure;
		this.comments = comments;
	}

	// Property accessors

	public Integer getOutboundDetailsId() {
		return this.outboundDetailsId;
	}

	public void setOutboundDetailsId(Integer outboundDetailsId) {
		this.outboundDetailsId = outboundDetailsId;
	}

	public Integer getOutboundId() {
		return this.outboundId;
	}

	public void setOutboundId(Integer outboundId) {
		this.outboundId = outboundId;
	}

	public Integer getFittingsInfoId() {
		return this.fittingsInfoId;
	}

	public void setFittingsInfoId(Integer fittingsInfoId) {
		this.fittingsInfoId = fittingsInfoId;
	}

	public Integer getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitcosts() {
		return this.unitcosts;
	}

	public void setUnitcosts(Double unitcosts) {
		this.unitcosts = unitcosts;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getFigure() {
		return this.figure;
	}

	public void setFigure(Double figure) {
		this.figure = figure;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}