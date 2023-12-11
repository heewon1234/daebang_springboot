package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Building")
public class Building {
	
	@Id
	@Column(name="building_id")
	private String buildingId;
	
	@Column(name="building_type")
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

	public Building(String buildingId, String buildingType) {
		super();
		this.buildingId = buildingId;
		this.buildingType = buildingType;
	}

	public Building() {
		super();
	}

}
