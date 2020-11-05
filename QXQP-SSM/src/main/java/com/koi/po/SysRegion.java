package com.koi.po;

/**
 * SysRegion entity. @author MyEclipse Persistence Tools
 */

public class SysRegion implements java.io.Serializable {

	// Fields

	private Integer regionId;
	private String regionName;
	private Integer sireId;
	private Integer viscountId;
	private String regionNum;
	private Boolean toDeactivate;

	// Constructors

	/** default constructor */
	public SysRegion() {
	}

	/** minimal constructor */
	public SysRegion(String regionName, Integer sireId, Integer viscountId,
			String regionNum) {
		this.regionName = regionName;
		this.sireId = sireId;
		this.viscountId = viscountId;
		this.regionNum = regionNum;
	}

	/** full constructor */
	public SysRegion(String regionName, Integer sireId, Integer viscountId,
			String regionNum, Boolean toDeactivate) {
		this.regionName = regionName;
		this.sireId = sireId;
		this.viscountId = viscountId;
		this.regionNum = regionNum;
		this.toDeactivate = toDeactivate;
	}

	// Property accessors

	public Integer getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getSireId() {
		return this.sireId;
	}

	public void setSireId(Integer sireId) {
		this.sireId = sireId;
	}

	public Integer getViscountId() {
		return this.viscountId;
	}

	public void setViscountId(Integer viscountId) {
		this.viscountId = viscountId;
	}

	public String getRegionNum() {
		return this.regionNum;
	}

	public void setRegionNum(String regionNum) {
		this.regionNum = regionNum;
	}

	public Boolean getToDeactivate() {
		return this.toDeactivate;
	}

	public void setToDeactivate(Boolean toDeactivate) {
		this.toDeactivate = toDeactivate;
	}

}