package com.koi.qxqp.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;
import com.bin.david.form.data.column.Column;
@SmartTable(name = "维修项目",count=true)
public class PreRepairItemDetailVo implements java.io.Serializable {
	private Integer preRepairItemDetailId;
	private Integer repairItemId;
	private Integer predateId;
	private Integer maintainabilityId;
	@SmartColumn(id =3,name = "修理费",autoCount=true)
	private Double repairCharge;
	@SmartColumn(id =4,name = "折扣(%)")
	private Double discount;
	@SmartColumn(id =5,name = "实收金额",autoCount=true)
	private Double amountPaid;
	@SmartColumn(id =7,name = "备注")
	private String remark;
	@SmartColumn(id =1,name = "修理项目")
	private String repairItemName;
	@SmartColumn(id =6,name = "维修性质")
	private String maintainabilityName;
	@SmartColumn(id =2,name = "维修工艺")
	private String maintenanceName;
	private Integer maintenanceId;
	public Integer getPreRepairItemDetailId() {
		return preRepairItemDetailId;
	}
	public void setPreRepairItemDetailId(Integer preRepairItemDetailId) {
		this.preRepairItemDetailId = preRepairItemDetailId;
	}
	public Integer getRepairItemId() {
		return repairItemId;
	}
	public void setRepairItemId(Integer repairItemId) {
		this.repairItemId = repairItemId;
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
	public Double getRepairCharge() {
		return repairCharge;
	}
	public void setRepairCharge(Double repairCharge) {
		this.repairCharge = repairCharge;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRepairItemName() {
		return repairItemName;
	}
	public void setRepairItemName(String repairItemName) {
		this.repairItemName = repairItemName;
	}
	public String getMaintainabilityName() {
		return maintainabilityName;
	}
	public void setMaintainabilityName(String maintainabilityName) {
		this.maintainabilityName = maintainabilityName;
	}
	public String getMaintenanceName() {
		return maintenanceName;
	}
	public void setMaintenanceName(String maintenanceName) {
		this.maintenanceName = maintenanceName;
	}
	public Integer getMaintenanceId(){
		return maintenanceId;
	}
	public void setMaintenanceId(Integer maintenanceId){
		this.maintenanceId=maintenanceId;
	}
}
