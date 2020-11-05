package com.koi.qxqp.bean;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

import java.io.Serializable;
@SmartTable(name = "其他费用",count=true)
public class PreOtherCostDetailVo implements Serializable{
	private Integer preOtherCostDetailId;
	private Integer predateId;
	private Integer expensesId;
	@SmartColumn(id =2,name = "金额",autoCount=true)
	private Double amount;
	@SmartColumn(id =3,name = "折扣(%)")
	private Double discount;
	@SmartColumn(id =4,name = "实收金额",autoCount = true)
	private Double amountPaid;
	@SmartColumn(id =5,name = "备注")
	private String remark;
	@SmartColumn(id =1,name = "费用名称")
	private String expensesName;

	public Integer getPreOtherCostDetailId() {
		return preOtherCostDetailId;
	}

	public void setPreOtherCostDetailId(Integer preOtherCostDetailId) {
		this.preOtherCostDetailId = preOtherCostDetailId;
	}

	public Integer getPredateId() {
		return predateId;
	}

	public void setPredateId(Integer predateId) {
		this.predateId = predateId;
	}

	public Integer getExpensesId() {
		return expensesId;
	}

	public void setExpensesId(Integer expensesId) {
		this.expensesId = expensesId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getExpensesName() {
		return expensesName;
	}

	public void setExpensesName(String expensesName) {
		this.expensesName = expensesName;
	}
	
}
