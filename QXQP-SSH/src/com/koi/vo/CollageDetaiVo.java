package com.koi.vo;

public class CollageDetaiVo {
	private Integer collageDetaiId;
    private Integer maintainabilityId;
    private Integer warehouseId;
    private Integer collageId;
    private Double quantity;
    private Double unitPrice;
    private Double amount;
    private String position;
    private String vehicleType;
    private String fittingsName;
    private String fittingsCode;
    private String fittingsSpec;
    private String systemUnit;
    
    private String maintainabilityName;
    private String maintenanceNum;
    private String warehouseName;
	
    public CollageDetaiVo(){}

	public CollageDetaiVo(Integer collageDetaiId, Integer maintainabilityId,
			Integer warehouseId, Integer collageId, Double quantity,
			Double unitPrice, Double amount, String position,
			String vehicleType, String fittingsName, String fittingsCode,
			String fittingsSpec, String systemUnit, String maintainabilityName,
			String maintenanceNum, String warehouseName) {
		super();
		this.collageDetaiId = collageDetaiId;
		this.maintainabilityId = maintainabilityId;
		this.warehouseId = warehouseId;
		this.collageId = collageId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.position = position;
		this.vehicleType = vehicleType;
		this.fittingsName = fittingsName;
		this.fittingsCode = fittingsCode;
		this.fittingsSpec = fittingsSpec;
		this.systemUnit = systemUnit;
		this.maintainabilityName = maintainabilityName;
		this.maintenanceNum = maintenanceNum;
		this.warehouseName = warehouseName;
	}

	public Integer getCollageDetaiId() {
		return collageDetaiId;
	}

	public void setCollageDetaiId(Integer collageDetaiId) {
		this.collageDetaiId = collageDetaiId;
	}

	public Integer getMaintainabilityId() {
		return maintainabilityId;
	}

	public void setMaintainabilityId(Integer maintainabilityId) {
		this.maintainabilityId = maintainabilityId;
	}

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public Integer getCollageId() {
		return collageId;
	}

	public void setCollageId(Integer collageId) {
		this.collageId = collageId;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getFittingsName() {
		return fittingsName;
	}

	public void setFittingsName(String fittingsName) {
		this.fittingsName = fittingsName;
	}

	public String getFittingsCode() {
		return fittingsCode;
	}

	public void setFittingsCode(String fittingsCode) {
		this.fittingsCode = fittingsCode;
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

	public String getMaintenanceNum() {
		return maintenanceNum;
	}

	public void setMaintenanceNum(String maintenanceNum) {
		this.maintenanceNum = maintenanceNum;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
    
    
	
	
}
