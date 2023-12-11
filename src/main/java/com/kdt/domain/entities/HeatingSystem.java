package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Heating_System")
public class HeatingSystem {
	
	@Id
	@Column(name="heating_id")
	private String heatingId;
	
	@Column(name="heating_type")
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

	public HeatingSystem(String heatingId, String heatingType) {
		super();
		this.heatingId = heatingId;
		this.heatingType = heatingType;
	}

	public HeatingSystem() {
		super();
	}


}
