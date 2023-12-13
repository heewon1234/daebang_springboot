package com.kdt.dto;

public class BuildingDTO {

	private String buildingId;
	private String buildingType;
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public BuildingDTO(String buildingId, String buildingType) {
		super();
		this.buildingId = buildingId;
		this.buildingType = buildingType;
	}
	public BuildingDTO() {
		super();
	}


}
