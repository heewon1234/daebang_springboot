package com.kdt.dto;

public class HeatingSystemDTO {

	private String heatingId;
	private String heatingType;
	
	public String getHeatingId() {
		return heatingId;
	}
	public void setHeatingId(String heatingId) {
		this.heatingId = heatingId;
	}
	public String getHeatingType() {
		return heatingType;
	}
	public void setHeatingType(String heatingType) {
		this.heatingType = heatingType;
	}
	public HeatingSystemDTO(String heatingId, String heatingType) {
		super();
		this.heatingId = heatingId;
		this.heatingType = heatingType;
	}
	public HeatingSystemDTO() {
		super();
	}
	
}
