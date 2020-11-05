package com.koi.vo;

public class ReturnJson {
	private Boolean state;
	private String text;
	private String objData;
	private String states;

	public ReturnJson() {
	}

	public ReturnJson(Boolean state, String text, String objData, String states) {
		super();
		this.state = state;
		this.text = text;
		this.objData = objData;
		this.states = states;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getObjData() {
		return objData;
	}

	public void setObjData(String objData) {
		this.objData = objData;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}

}
